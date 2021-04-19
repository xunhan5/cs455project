package com.example.cs455

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playButtonClick(view : View){
        val intent = Intent(this,PlayerSetup::class.java)
        startActivity(intent)
    }

    fun playWithComputer(view : View){
        val intent = Intent(this,ComputerDisplay::class.java)
        this.startActivity(intent)
    }



}