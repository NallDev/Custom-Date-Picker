package com.nalldev.datepicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.NumberPicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal class DateWheelPicker(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs), CoroutineScope by CoroutineScope(
    Dispatchers.Default) {
    private var dayPicker: NumberPicker
    private var monthPicker: NumberPicker
    private var yearPicker: NumberPicker
    private val calendar: Calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private var job: Job? = null

    init {
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.date_wheel_picker, this, true)

        dayPicker = findViewById(R.id.dayPicker)
        monthPicker = findViewById(R.id.monthPicker)
        yearPicker = findViewById(R.id.yearPicker)

        setupMonthPicker()
        setupYearPicker()
        updatePickersForCurrentDate()

        dayPicker.setOnValueChangedListener { _, _, _ ->
            notifyDateChange()
        }

        monthPicker.setOnValueChangedListener { _, _, newMonth ->
            setupDayPicker(newMonth, yearPicker.value)
        }

        yearPicker.setOnValueChangedListener { _, _, newYear ->
            setupDayPicker(monthPicker.value, newYear)
        }
    }

    private fun setupMonthPicker() {
        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.displayedValues = months
    }

    private fun setupYearPicker() {
        val currentYear = calendar.get(Calendar.YEAR)
        yearPicker.minValue = currentYear - 30
        yearPicker.maxValue = currentYear + 30
    }

    private fun setupDayPicker(month: Int, year: Int) {
        calendar.set(year, month - 1, 1)
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        if (dayPicker.maxValue != maxDay) {
            dayPicker.maxValue = maxDay
            dayPicker.minValue = 1
        }
        notifyDateChange()
    }

    private fun updatePickersForCurrentDate() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        yearPicker.value = year
        monthPicker.value = month
        setupDayPicker(month, year)
        dayPicker.value = day
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancel()
    }

    private fun notifyDateChange() {
        job?.cancel()
        job = launch {
            val date = getDate()
            withContext(Dispatchers.Main) {
                dateChangeListener?.onDateChange(date)
            }
        }
    }

    fun getDate(): String {
        synchronized(calendar) {
            calendar.set(yearPicker.value, monthPicker.value - 1, dayPicker.value)
            return dateFormat.format(calendar.time)
        }
    }

    interface OnDateChangeListener {
        fun onDateChange(date: String)
    }

    var dateChangeListener: OnDateChangeListener? = null
}