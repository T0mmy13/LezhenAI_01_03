package com.bignerdranch.android.lezhen_ai_01_var_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SecondScreen : AppCompatActivity() {

    private lateinit var ValuesFrom: EditText
    private lateinit var ValuesIn: EditText
    private lateinit var ButtonWriteResult: Button
    private lateinit var EditTextInput: EditText
    private lateinit var TextViewResult: TextView
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

        ButtonWriteResult.setOnClickListener{
            ConversionFromTo(ValuesFrom.text.toString(), ValuesIn.text.toString(), EditTextInput.text.toString())
        }
    }
    private fun ConversionFromTo(ValueFrom :String, ValueIn: String, numb : String){
        if (ValuesListConvert.contains(ValueFrom.lowercase()) && ValuesListConvert.contains(ValueIn.lowercase())){
            if(CheckCorrectnessСhoice(ValueFrom.lowercase(), ValueIn.lowercase())){
                text = "Ошибка!\nНельзя перевести меньшее значение в большее"
                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
            }
            else{
                val Numb : Int? = numb.toIntOrNull()
                if (Numb != null){
                    TextViewResult.text =
                        FindUnswer(ValueFrom.lowercase(), ValueIn.lowercase(), Numb).toString()
                }
                else{
                    text = "вы не ввели значение для перевода"
                    val toast = Toast.makeText(this, text, duration) // in Activity
                    toast.show()
                }
            }
        }
        else{
            text = "Неверный ввод"
            val toast = Toast.makeText(this, text, duration) // in Activity
            toast.show()
        }
    }
    private fun CheckCorrectnessСhoice(ValueFrom :String, ValueIn: String) : Boolean{
        var FirstValues = ValuesListConvert.indexOf(ValueFrom)
        var SecondValues = ValuesListConvert.indexOf(ValueIn)
        if(FirstValues < SecondValues){
            return true
        }
        else{
            return false;
        }
    }
    private fun FindUnswer(ValueFrom :String, ValueIn: String, Input : Int) : Int{
        var SecondValues = ValuesListConvert.indexOf(ValueFrom)
        var FirstValues = ValuesListConvert.indexOf(ValueIn)

        if(FirstValues == SecondValues){
            return Input
        }
        else if(SecondValues == 3){
            if(FirstValues == 2){
                return 1024*Input
            }
            else if(FirstValues == 1){
                return 1048576*Input
            }
            else if(FirstValues == 0){
                return 1073741824*Input
            }
        }
        else if(SecondValues == 2){
            if(FirstValues == 1){
                return 1024*Input
            }
            else if(FirstValues == 0){
                return 1048576*Input
            }
        }
        else if (SecondValues == 1) {
            if (FirstValues == 0) {
                return 1024*Input
            }
        }
        else{
            return -1
        }
        return TODO("Provide the return value")
    }
}