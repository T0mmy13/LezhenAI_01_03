package com.bignerdranch.android.lezhen_ai_01_var_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FirstScreen : AppCompatActivity() {

    private lateinit var GoNextScreenButon: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        GoNextScreenButon = findViewById(R.id.ButtonGoNextScreen)
        GoNextScreenButon.setOnClickListener{
            val intent: Intent = Intent(this@FirstScreen, SecondScreen::class.java)
            startActivity(intent)
        }
    }
}