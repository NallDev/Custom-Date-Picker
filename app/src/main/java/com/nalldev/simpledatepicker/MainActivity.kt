package com.nalldev.simpledatepicker

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nalldev.datepicker.NalDatePicker

class MainActivity : AppCompatActivity() {

    private val nalDatePicker = NalDatePicker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val word = findViewById<TextView>(R.id.hello)
        nalDatePicker.onSaveListener = { date ->
            Log.e("DATE", date)
        }
        nalDatePicker.onDateChangeListener = { date ->
            Log.e("DATE", date)
        }
        word.setOnClickListener {
            nalDatePicker.show(supportFragmentManager, "datepicker")
        }
    }
}