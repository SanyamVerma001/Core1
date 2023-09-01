package com.example.core1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var diceRollLabel: TextView
    private lateinit var scoreLabel: TextView
    private lateinit var diceImageView: ImageView
    private var score = 0
    private var diceRolled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        diceRollLabel = findViewById(R.id.diceRollLabel)
       scoreLabel = findViewById(R.id.scoreLabel)

        // Set click listeners for buttons
        val rollButton = findViewById<Button>(R.id.rollButton)
        rollButton.setOnClickListener { rollDice() }

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { addScore() }

        val subtractButton = findViewById<Button>(R.id.subtractButton)
        subtractButton.setOnClickListener { subtractScore() }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener { resetScore() }

        updateUI()
    }

    private fun rollDice() {
        // Generate random dice value (for example, 1 to 6)
        val diceValue = (1..6).random()
        diceRollLabel.text = diceValue.toString()
        //val diceImageView = ImageView.TEXT_ALIGNMENT_VIEW_END
        diceImageView.setImageResource(getDiceImageResource(diceValue))
        diceRolled = true

        // Change text color based on the dice value
        if (diceValue > 20) {
            scoreLabel.setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {
            scoreLabel.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
        updateUI()
    }



    private fun getDiceImageResource(diceValue: Int): Int {
        return when (diceValue) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1 // Default image for invalid roll value
        }
    }


    private fun addScore() {
        if (diceRolled) {
            score += diceRollLabel.text.toString().toInt()
            diceRolled = false
            updateUI()
        }
    }

    private fun subtractScore() {
        if (diceRolled) {
            score = maxOf(0, score - diceRollLabel.text.toString().toInt())
            diceRolled = false
            updateUI()
        }
    }

    private fun resetScore() {
        score = 0
        diceRolled = false
        updateUI()
    }

    private fun updateUI() {
        scoreLabel.text = score.toString()
        // Implement logic to change score label color based on score value
        // For example: if (score < 19) scoreLabel.setTextColor(Color.BLACK)
        // ... (similarly for red and green)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", score)
        outState.putBoolean("diceRolled", diceRolled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        score = savedInstanceState.getInt("score")
        diceRolled = savedInstanceState.getBoolean("diceRolled")
        updateUI()
    }

    // Add log messages for testing and debugging
    private fun logDebugMessage(message: String) {
        Log.d("MyApp", message)
    }
}
