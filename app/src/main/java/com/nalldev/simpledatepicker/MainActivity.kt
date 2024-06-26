package com.nalldev.simpledatepicker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nalldev.datepicker.NalDatePicker
import com.nalldev.naltoast.util.Type
import com.nalldev.naltoast.view.NalToast
import com.nalldev.simpledatepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val successToast by lazy { NalToast(binding.root, Type.SUCCESS) }
    private val datePicker by lazy { NalDatePicker() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.apply {
            datePicker.apply {
                onSaveListener = {
                    successToast.show("Date : $it")
                }

                onDateChangeListener = {
                    placeholder.text = String.format("Date : %s", it)
                }
            }


            btnShow.setOnClickListener {
                datePicker.show(supportFragmentManager, "datePicker")
            }
        }
    }
}