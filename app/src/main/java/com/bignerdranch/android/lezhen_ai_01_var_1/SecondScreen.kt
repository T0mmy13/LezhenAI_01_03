package com.bignerdranch.android.lezhen_ai_01_var_1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondScreen : AppCompatActivity() {

    private lateinit var ValuesFrom: EditText
    private lateinit var ValuesIn: EditText
    private lateinit var ButtonWriteResult: Button
    private lateinit var EditTextInput: EditText
    private lateinit var TextViewResult: TextView
    private lateinit var SharedPref: SharedPreferences
    var ValuesListConvert = arrayListOf<String>("байт", "килобайт", "мегабайт", "гигабайт")
    val duration = Toast.LENGTH_SHORT
    var text = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        ButtonWriteResult = findViewById(R.id.ButtonWriteResult)
        ValuesFrom = findViewById(R.id.EditTextValueFrom)
        ValuesIn = findViewById(R.id.EditTextValuesIn)
        EditTextInput = findViewById(R.id.EditTextInput)
        TextViewResult = findViewById(R.id.TextViewResult)
        SharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = SharedPref.edit()

        ButtonWriteResult.setOnClickListener{
            var ValueFrom = ValuesFrom.text.toString()
            var ValueIn = ValuesIn.text.toString()
            var numb = EditTextInput.text.toString()
            if (ValuesListConvert.contains(ValueFrom.lowercase()) && ValuesListConvert.contains(ValueIn.lowercase()))
            {
                var FirstValues = ValuesListConvert.indexOf(ValueFrom)
                var SecondValues = ValuesListConvert.indexOf(ValueIn)
                if(FirstValues > SecondValues){
                    val Numb: Int? = numb.toIntOrNull()
                    if (Numb != null) {
                        var SecondValues = ValuesListConvert.indexOf(ValueFrom)
                        var FirstValues = ValuesListConvert.indexOf(ValueIn)

                        if(FirstValues == SecondValues){
                            TextViewResult.text = Numb.toString()
                        }
                        else if(SecondValues == 3){
                            if(FirstValues == 2){
                                TextViewResult.text = (1024*Numb).toString()
                            }
                            else if(FirstValues == 1){
                                TextViewResult.text = (1048576*Numb).toString()
                            }
                            else if(FirstValues == 0){
                                TextViewResult.text = (1073741824*Numb).toString()
                            }
                        }
                        else if(SecondValues == 2){
                            if(FirstValues == 1){
                                TextViewResult.text = (1024*Numb).toString()
                            }
                            else if(FirstValues == 0){
                                TextViewResult.text = (1048576*Numb).toString()
                            }
                        }
                        else if (SecondValues == 1) {
                            if (FirstValues == 0) {
                                TextViewResult.text = (1024*Numb).toString()
                            }
                        }
                        else{
                            TextViewResult.text = (-1).toString()
                        }
                        editor.putString("Result", TextViewResult.text.toString())
                        editor.apply()
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(5000)
                            val intent = Intent(this@SecondScreen, ThirdScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        text = "вы не ввели значение для перевода"
                        val toast = Toast.makeText(this, text, duration) // in Activity
                        toast.show()
                    }
                }
                else{
                    text = "Ошибка!\nНельзя перевести меньшее значение в большее"
                    val toast = Toast.makeText(this, text, duration) // in Activity
                    toast.show()
                }
            }
            else{
                text = "Неверный ввод"
                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
            }


        }
    }
}