package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set min and max value
        binding.weightpicker.minValue = 30
        binding.weightpicker.maxValue = 150

        binding.heightpicker.minValue = 100
        binding.heightpicker.maxValue = 250

        binding.weightpicker.setOnValueChangedListener {_,_,_ ->
            calculateBMI()
        }
        binding.heightpicker.setOnValueChangedListener { _,_,_ ->
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val height = binding.heightpicker.value
        val doubleheight = height.toDouble() / 100

        val weight = binding.weightpicker.value
        val bmi = weight.toDouble() / (doubleheight * doubleheight)

        binding.resulttv.text = String.format("Your BMI Result is = %.2f", bmi)
        binding.healthtv.text = String.format("Considered = %s", healthyMessage(bmi))
    }

    private fun healthyMessage(bmi : Double): String{

        if(bmi < 18.5)
            return "Underweight"
        if(bmi < 25.0)
            return "Healthy"
        if(bmi < 30.0)
            return "Overweight"
        return "Plumpy"
    }

}