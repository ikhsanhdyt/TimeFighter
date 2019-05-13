package com.example.timefighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Deklarasi Variabel
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal lateinit var tapMeButton: TextView

    internal var score = 0
    //Boolean untuk memulai permainan
    internal var gameStarted = false

    internal lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var timeLeft = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Koneksikan Text View ke Variabel
        gameScoreTextView = findViewById(R.id.tv_game_score)
        timeLeftTextView = findViewById(R.id.tv_time_left)
        tapMeButton = findViewById(R.id.btn_tap_me)

        tapMeButton.setOnClickListener { v -> incrementScore()  }

        resetGame()

    }

    private fun incrementScore(){
        if (!gameStarted){
            startGame()
        }
        score++

        val newScore = getString(R.string.your_score , Integer.toString(score))
        gameScoreTextView.text = newScore


    }
    private fun resetGame(){
        score = 0

        val initialScore = getString(R.string.your_score,
            Integer.toString(score))
        gameScoreTextView.text = initialScore

        val initialTimeLeft = getString(R.string.time_left,
            Integer.toString(60))
        timeLeftTextView.text = initialTimeLeft

        countDownTimer = object : CountDownTimer(initialCountDown,
            countDownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000

                val timeLeftString = getString(R.string.time_left,
                    Integer.toString(timeLeft))
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish() {
                endGame()
            }
        }

        gameStarted = false

    }
    private fun startGame(){

        countDownTimer.start()
        gameStarted = true

    }
    private fun endGame(){

        Toast.makeText(this,getString(R.string.game_over_message,
            Integer.toString(score)),Toast.LENGTH_LONG).show()
        resetGame()


    }
}
