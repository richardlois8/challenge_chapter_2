package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
    }

    fun setupListener(){
        val etCost = findViewById<EditText>(R.id.etCost)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val swRound = findViewById<Switch>(R.id.swRound)
        val rgService = findViewById<RadioGroup>(R.id.rgService)
        val tvAmount = findViewById<TextView>(R.id.tvAmount)
        var rbService : RadioButton? = null

        rgService.setOnCheckedChangeListener { radioGroup, i ->
            rbService = findViewById(i)
        }

        btnCalculate.setOnClickListener {
            var totalTip = calculateTip(etCost.text.toString().toDouble(), rbService?.text.toString())
            if(swRound.isChecked){
                totalTip = totalTip.roundToInt().toDouble()
            }
            tvAmount.text = "Total tip: $totalTip"
            AlertDialog.Builder(this).setTitle("Tip").setMessage("Total tip: $totalTip").setNeutralButton("OK", null).show()
        }
    }

    fun calculateTip(cost : Double, service : String): Double{
        val tip = when(service){
            "Amazing (20%)" -> cost * 0.2
            "Good (18%)" -> cost * 0.18
            "OK (15%)" -> cost * 0.15
            else -> 0.0
        }
        return tip
    }

}