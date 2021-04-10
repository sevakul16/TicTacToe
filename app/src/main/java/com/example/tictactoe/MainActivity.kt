package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flag = findViewById<Switch>(R.id.switch1)
        val text1 = findViewById<TextView>(R.id.txt2)
        val text2 = findViewById<EditText>(R.id.PersonName2)
        flag.setOnCheckedChangeListener { buttonView, isChecked ->
            if (flag.isChecked) {
                text1.setVisibility(View.INVISIBLE)
                text2.setVisibility(View.INVISIBLE)
            } else {
                text1.setVisibility(View.VISIBLE)
                text2.setVisibility(View.VISIBLE)
            }
        }
    }

    fun buClick(view: View) {
        val buSelected = view as Button
        var cellID = 0
        when (buSelected.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }
        playGame(cellID, buSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1
    var counter = 0



    private fun playGame(cellID: Int, buSelected: Button) {
        val flag = findViewById<Switch>(R.id.switch1)
        if(activePlayer==1) {
            buSelected.text = "X"
            buSelected.setBackgroundColor(Color.parseColor("#346eeb"))
            player1.add(cellID)
            activePlayer = 2
            if (flag.isChecked) PVE()
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundColor(Color.parseColor("#34eb40"))
            player2.add(cellID)
            activePlayer = 1
        }
        counter ++
        buSelected.isEnabled = false

        checkWinner();
    }

    //private val textViewPlayer1: TextView? = null

    private fun checkWinner() {
        var winner = -1
        val tabloid: TextView = findViewById(resources.getIdentifier("txt1","id", packageName))
        val p1: TextView = findViewById(resources.getIdentifier("PersonName1","id", packageName))
        val p2: TextView = findViewById(resources.getIdentifier("PersonName2","id", packageName))

        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //col1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //col2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //col3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //diag1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //diag2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if ((winner == -1)&&(counter==9)) {
            //Toast.makeText(this, "It is draw", Toast.LENGTH_LONG).show()
            tabloid.text = "It is draw"

        }
        val flag = findViewById<Switch>(R.id.switch1)
        if (winner != -1) {
            if (winner==1) {
                tabloid.text = p1.text.toString() + " wins!"
            } else {
                if (flag.isChecked)
                    tabloid.text = "Computer wins!"
                else
                    tabloid.text = p2.text.toString() + " wins!"
            }
        }
    }
    private fun PVE() {
        var emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if(!(player1.contains(cellID))||(player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }
        val r = Random()
        val randIndex = r.nextInt(emptyCells.size - 0)+0
        val cellID = emptyCells[randIndex]

        val buSelected:Button
        when(cellID) {
            1 -> buSelected = bu1
            2 -> buSelected = bu2
            3 -> buSelected = bu3
            4 -> buSelected = bu4
            5 -> buSelected = bu5
            6 -> buSelected = bu6
            7 -> buSelected = bu7
            8 -> buSelected = bu8
            9 -> buSelected = bu9
            else -> buSelected = bu1
        }
        playGame(cellID, buSelected)
    }
}