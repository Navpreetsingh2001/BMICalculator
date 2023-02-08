package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.log
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var weightText =binding.etWeight
        var heightText =binding.etHeight
        var cal_Button =binding.btnCalculate

        cal_Button.setOnClickListener {

            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)


            }
        }
    }

    private fun validInput(weight:String ,height:String):Boolean{


        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "weight is empty", Toast.LENGTH_SHORT).show()
                binding.tvIndex.text=""
                binding.tvResult.text=""
                binding.tvInfo.text=""
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "height is empty", Toast.LENGTH_SHORT).show()
                return false

            }
            else->{
                return true
            }
        }
    }


    private fun displayResult(bmi:Float){
        val resultIndex =binding.tvIndex
        val resultDescription =binding.tvResult
        val info =binding.tvInfo

        var resultText =""
        var color =0

        resultIndex.text=bmi.toString()
        info.text="(Normal range is 18.5 -24.9)"

        when{
            bmi <18.50 ->{
                resultText ="Underweight"
                color=R.color.underWeight
            }
            bmi in 18.50..24.99 ->{
                resultText ="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText ="Overweight"
                color=R.color.overWeight
            }
            bmi >29.99 ->{
                resultText ="Obese"
                color=R.color.obese
            }
        }

        resultDescription.text="You are $resultText"
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
    }
}