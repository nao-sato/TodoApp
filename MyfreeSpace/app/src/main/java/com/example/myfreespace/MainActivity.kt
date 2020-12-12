package com.example.myfreespace

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    abstract class Car(var maxGas: Int = 0)

    class prius(maxGas: Int =30 ) : Car(maxGas)

    class Ferrari (maxGas: Int = 40) : Car(maxGas)

    fun GasStation (car: Car):String{
        return "補給されました。${car.maxGas}L"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val show = findViewById<TextView>(R.id.baka)
        val P = prius()
        val F = Ferrari()

        show.text = GasStation(F)
        }
    }

