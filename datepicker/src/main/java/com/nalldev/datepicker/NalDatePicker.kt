package com.nalldev.datepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nalldev.datepicker.databinding.BottomSheetPickerBinding

class NalDatePicker : BottomSheetDialogFragment() {
    private var _binding : BottomSheetPickerBinding? = null
    private val binding get() = _binding!!

    /**
     * Listener that gets triggered when the save button is clicked.
     * This listener returns the selected date as a string in the format "dd/MM/YYYY".
     */
    private var onSaveListener: ((String) -> Unit)? = null

    /**
     * Listener that gets triggered when the value is changed.
     * This listener returns the selected date as a string in the format "dd/MM/YYYY".
     */
    var onDateChangeListener: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEventListener()
    }

    private fun initEventListener() = with(binding) {
        btnSave.setOnClickListener {
            onSaveListener?.invoke(dwp.getDate())
            dialog?.dismiss()
        }

        dwp.dateChangeListener = object : DateWheelPicker.OnDateChangeListener {
            override fun onDateChange(date: String) {
                onDateChangeListener?.invoke(date)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}