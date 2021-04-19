package com.example.cs455


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class PlayerSetup : AppCompatActivity() {

    lateinit var player1: EditText
    lateinit var player2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_setup)

        player1 = findViewById(R.id.editTextTextPersonName)
        player2 = findViewById(R.id.editTextTextPersonName2)
    }


    fun submitButtonClick(view: View) {

        val player1Name = player1.text.toString()
        val player2Name = player2.text.toString()


        val arrayOfNames = arrayListOf<String>()
        arrayOfNames.add(player1Name)
        arrayOfNames.add(player2Name)

        val intent = Intent(this, GamePlayerDisplay::class.java)
        intent.putExtra("Player_Names", arrayOfNames)

        startActivity(intent)
    }
}
