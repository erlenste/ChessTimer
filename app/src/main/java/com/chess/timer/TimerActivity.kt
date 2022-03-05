package com.chess.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import java.util.*
import java.util.concurrent.TimeUnit

class TimerActivity : AppCompatActivity() {

    var timeSpent1 = 0
    var timeSpent2 = 0

    var timer1running = false;
    var timer2running = false;

    private lateinit var countDownTimer1 : CountDownTimer;
    private lateinit var countDownTimer2 : CountDownTimer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val timer1 : Button = findViewById<Button>(R.id.timer1)
        val timer2 : Button = findViewById<Button>(R.id.timer2)

        val time = intent.getIntExtra("time", 15*60)
        val increment = intent.getIntExtra("increment", 15)

        timer1.text = fromMinutesToHHmm(time*60)
        timer2.text = fromMinutesToHHmm(time*60)


        fun startTimer1(num : Long) {
            timer1running = true;
            countDownTimer1 = object : CountDownTimer(num, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timer1.text = fromMinutesToHHmm(millisUntilFinished.toInt() / 1000)
                    timeSpent1 += 1
                }

                override fun onFinish() {
                    timer1.text = "Done!"
                }
            }.start()
        }

        fun startTimer2(num : Long) {
            timer2running = true
            countDownTimer2 = object : CountDownTimer(num, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timer2.text = fromMinutesToHHmm(millisUntilFinished.toInt() / 1000)
                    timeSpent2 += 1
                }

                override fun onFinish() {
                    timer2.text = "Done!"
                }
            }.start()
        }

        fun pauseTimer1() {
            if (this::countDownTimer1.isInitialized) {
                countDownTimer1.cancel()
                timer1running = false;
            }
        }

        fun pauseTimer2() {
            if (this::countDownTimer2.isInitialized) {
                countDownTimer2.cancel()
                timer2running = false;
            }
        }

        timer1.setOnClickListener {
            if (timer2running) return@setOnClickListener
            pauseTimer1()
            timeSpent2 -= increment
            startTimer2((time*60 - timeSpent2).toLong() * 1000)
        }

        timer2.setOnClickListener {
            if (timer1running) return@setOnClickListener
            pauseTimer2()
            timeSpent1 -= increment
            startTimer1((time*60 - timeSpent1).toLong()*1000)
        }

    }

    fun fromMinutesToHHmm(minutes: Int): String {
        val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
        val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
        return String.format("%02d:%02d", hours, remainMinutes)
    }






}