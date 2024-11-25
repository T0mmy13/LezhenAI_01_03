package com.bignerdranch.android.lezhen_ai_01_var_1

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ThirdScreen : AppCompatActivity() {

    private lateinit var SharedPref: SharedPreferences
    private lateinit var TextViewResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        SharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        TextViewResult = findViewById(R.id.TextViewResultThirdScreen)
        TextViewResult.text = SharedPref.getString("Result", "") ?: ""

    }
}