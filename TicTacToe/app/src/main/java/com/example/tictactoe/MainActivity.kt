package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var player1 = ArrayList<Int>();
    var player2 = ArrayList<Int>();
    var activePlayer = 1
    var count=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
Toast.makeText(this,"Start Playing!",Toast.LENGTH_LONG).show()
    }

    fun bClick(view: View) {
        val bClicked = view as Button
        var cellId = 0
        when (bClicked.id) {
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9
        }
        playGame(cellId, bClicked)

    }


    private fun playGame(cellId: Int, bClicked: Button) {
        if (activePlayer == 1) {
            bClicked.text = "X"
            player1.add(cellId)
            count+=1

            //player2Computer
            if(count<=8){
            val random = Random()
            var rbClicked: Button? = null
            var rcellId = random.nextInt(1..9)
            var bool: Boolean = false
            while (bool == false) {
                rcellId = random.nextInt(1..9)
                if (!player1.contains(rcellId) && !player2.contains(rcellId))
                    bool = true

            }
            when (rcellId) {
                1 -> rbClicked = b1
                2 -> rbClicked = b2
                3 -> rbClicked = b3
                4 -> rbClicked = b4
                5 -> rbClicked = b5
                6 -> rbClicked = b6
                7 -> rbClicked = b7
                8 -> rbClicked = b8
                9 -> rbClicked = b9
            }
            rbClicked!!.text = "O"
            player2.add(rcellId)

                rbClicked!!.isEnabled=false
                count+=1
            }
            bClicked.isEnabled = false

        }

        checkWinner()

    }

    private fun checkWinner() {
        var winner = -1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
            b1.setTextColor(Color.GREEN)
            b2.setTextColor(Color.GREEN)
            b3.setTextColor(Color.GREEN)
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
            b1.setTextColor(Color.RED)
            b2.setTextColor(Color.RED)
            b3.setTextColor(Color.RED)
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
            b4.setTextColor(Color.GREEN)
            b5.setTextColor(Color.GREEN)
            b6.setTextColor(Color.GREEN)
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
            b4.setTextColor(Color.RED)
            b5.setTextColor(Color.RED)
            b6.setTextColor(Color.RED)
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
            b7.setTextColor(Color.GREEN)
            b8.setTextColor(Color.GREEN)
            b9.setTextColor(Color.GREEN)
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
            b7.setTextColor(Color.RED)
            b8.setTextColor(Color.RED)
            b9.setTextColor(Color.RED)
        }
        //col1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
            b1.setTextColor(Color.GREEN)
            b4.setTextColor(Color.GREEN)
            b7.setTextColor(Color.GREEN)
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
            b1.setTextColor(Color.RED)
            b4.setTextColor(Color.RED)
            b7.setTextColor(Color.RED)
        }
        //col2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
            b2.setTextColor(Color.GREEN)
            b5.setTextColor(Color.GREEN)
            b8.setTextColor(Color.GREEN)
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
            b2.setTextColor(Color.RED)
            b5.setTextColor(Color.RED)
            b8.setTextColor(Color.RED)
        }
        //col3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
            b3.setTextColor(Color.GREEN)
            b6.setTextColor(Color.GREEN)
            b9.setTextColor(Color.GREEN)
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
            b3.setTextColor(Color.RED)
            b6.setTextColor(Color.RED)
            b9.setTextColor(Color.RED)
        }
        //Diagonal1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
            b1.setTextColor(Color.GREEN)
            b5.setTextColor(Color.GREEN)
            b9.setTextColor(Color.GREEN)
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
            b1.setTextColor(Color.RED)
            b5.setTextColor(Color.RED)
            b9.setTextColor(Color.RED)
        }
        //Diagonal2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
            b3.setTextColor(Color.GREEN)
            b5.setTextColor(Color.GREEN)
            b7.setTextColor(Color.GREEN)
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
            b3.setTextColor(Color.RED)
            b5.setTextColor(Color.RED)
            b7.setTextColor(Color.RED)
        }
        if(winner==-1&&b1.isEnabled==false&&b2.isEnabled==false&&b3.isEnabled==false&&b4.isEnabled==false&&b5.isEnabled==false&&b6.isEnabled==false&&b7.isEnabled==false&&b8.isEnabled==false&&b9.isEnabled==false){
            Toast.makeText(this,"Game Over!",Toast.LENGTH_SHORT).show()
            b1.setTextColor(Color.RED)
            b2.setTextColor(Color.RED)
            b3.setTextColor(Color.RED)
            b4.setTextColor(Color.RED)
            b5.setTextColor(Color.RED)
            b6.setTextColor(Color.RED)
            b7.setTextColor(Color.RED)
            b8.setTextColor(Color.RED)
            b9.setTextColor(Color.RED)
            resetBtn.visibility=View.VISIBLE
        }

        if (winner == 1) {
            Toast.makeText(this, "You Won!", Toast.LENGTH_LONG).show()
            resetBtn.visibility = View.VISIBLE
        } else if (winner == 2) {
            Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).show()
            resetBtn.visibility = View.VISIBLE
        }
    }

    fun reset(view: View) {

        b1.text = ""
        b2.text = ""
        b3.text = ""
        b4.text = ""
        b5.text = ""
        b6.text = ""
        b7.text = ""
        b8.text = ""
        b9.text = ""

        b1.isEnabled = true
        b2.isEnabled = true
        b3.isEnabled = true
        b4.isEnabled = true
        b5.isEnabled = true
        b6.isEnabled = true
        b7.isEnabled = true
        b8.isEnabled = true
        b9.isEnabled = true

        b1.setTextColor(Color.BLACK)
        b2.setTextColor(Color.BLACK)
        b3.setTextColor(Color.BLACK)
        b4.setTextColor(Color.BLACK)
        b5.setTextColor(Color.BLACK)
        b6.setTextColor(Color.BLACK)
        b7.setTextColor(Color.BLACK)
        b8.setTextColor(Color.BLACK)
        b9.setTextColor(Color.BLACK)

        resetBtn.visibility = View.INVISIBLE
        player1.clear()
        player2.clear()
        activePlayer = 1
        count=0

    }


}

private fun Random.nextInt(intRange: IntRange): Int {
return intRange.start + nextInt(intRange.last - intRange.start);
}
