package com.example.cs455


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.game_display.*
import java.util.*
import kotlin.collections.ArrayList


class GamePlayerDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_display)
        val placeholder = findViewById<TextView>(R.id.placeHolder)
        placeholder.text =
            intent.getStringArrayListExtra("Player_Names")?.get(0).toString() + "'s turn "
    }

    companion object {
        var activePlayer = 1
        var player1 = ArrayList<Int>()
        var player2 = ArrayList<Int>()
        val listOfUsedButton = mutableListOf<Int>()
    }

    fun homeButtonClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun playAgainButtonClick(view: View) {
        restartGame()
    }


    fun buClick(view: View) {
        val buSelected = view as Button
        var cellId = 0
        when (buSelected.id) {

            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }

        playGame(cellId, buSelected)
    }

    private fun playGame(cellId: Int, buSelected: Button) {


        if (activePlayer == 1) {
            val placeholder = findViewById<TextView>(R.id.placeHolder)
            placeholder.text =
                intent.getStringArrayListExtra("Player_Names")?.get(1).toString() + "'s turn "
            buSelected.text = "X"
            // buSelected.setBackgroundResource(R.color.colorAccent)
            buSelected.setTextColor(resources.getColor(R.color.colorAccent))
            player1.add(cellId)
            activePlayer = 2
            // autoplay()
        } else {
            val placeholder = findViewById<TextView>(R.id.placeHolder)
            placeholder.text =
                intent.getStringArrayListExtra("Player_Names")?.get(0).toString() + "'s turn "
            buSelected.text = "O"
            //  buSelected.setBackgroundResource(R.color.orange)
            buSelected.setTextColor(resources.getColor(R.color.orange))
            player2.add(cellId)
            activePlayer = 1
        }

        buSelected.isEnabled = false

        checkWinner(buSelected)

    }


    fun checkWinner(buSelected: Button) {

        val extras = intent.extras
        val arrayOfNames = extras?.getStringArrayList("Player_Names")
        listOfUsedButton.add(buSelected.id)

        var winner = -1

        //from  1

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //from  2

        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //from  3

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //from  4

        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        } else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }


        //from 7
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //check tie
        if (listOfUsedButton.size == 9) {
            winner = 3
        }


        //determine next action based on state of winner
        if (winner == 1) {
            Toast.makeText(this, "${arrayOfNames?.get(0)} is the winner ", Toast.LENGTH_SHORT).show()
            player1winsCounts += 1
            restartGame()

        } else if (winner == 2) {
            Toast.makeText(this, "${arrayOfNames?.get(1)} is the winner ", Toast.LENGTH_SHORT).show()
            player2winsCounts += 1
            restartGame()
        }
        if (winner == 3 && (winner != 1 || winner != 2)) {
            Toast.makeText(this, "Game Ended in a  Tie", Toast.LENGTH_SHORT).show()
            restartGame()

        }
    }


    var player1winsCounts = 0
    var player2winsCounts = 0


    fun restartGame() {

        activePlayer = 1
        player1.clear()
        player2.clear()
        listOfUsedButton.clear()

        for (cellId in 1..9) {

            var buSelected: Button? = when (cellId) {
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {
                    button1
                }
            }

            buSelected!!.text = ""
            // buSelected.setBackgroundResource(R.color.white)
            buSelected.setHintTextColor(R.color.defaultButton)
            buSelected.isEnabled = true
            val placeholder = findViewById<TextView>(R.id.placeHolder)
            placeholder.text =
                intent.getStringArrayListExtra("Player_Names")?.get(0).toString() + "'s turn "
        }
        Toast.makeText(
            this,
            "Player 1:$player1winsCounts, Player2: $player2winsCounts",
            Toast.LENGTH_LONG
        ).show()
    }

    fun autoplay() {

        var emptyCells = ArrayList<Int>()

        for (cellId in 1..9) {


            if (!(player1.contains(cellId) || player2.contains(cellId))) {

                emptyCells.add(cellId)
            }
        }

        var r = Random()
        val randIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randIndex]

        var buSelected: Button?

        buSelected = when (cellId) {

            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9

            else -> button1
        }


        playGame(cellId, buSelected)
    }

}