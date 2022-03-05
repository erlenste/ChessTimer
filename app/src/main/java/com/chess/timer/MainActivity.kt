package com.chess.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTime: SeekBar = findViewById(R.id.mainTime) as SeekBar
        val mainTimeText: TextView = findViewById<TextView>(R.id.mainTimeText)

        val mainIncrement: SeekBar = findViewById(R.id.mainIncrement) as SeekBar
        val mainIncrementText: TextView = findViewById<TextView>(R.id.mainIncrementText)

        val blitzButton : Button = findViewById(R.id.blitzButton)
        val rapidButton: Button = findViewById(R.id.rapidButton)
        val classicButton: Button = findViewById(R.id.classicButton)
        val lardalgatenButton: Button = findViewById(R.id.lardalgatenButton)
        val competitorButton: Button = findViewById(R.id.competitorButton)

        var timeCount = 0;
        var incrementCount= 0;

        val startButton: Button = findViewById<Button>(R.id.startButton)
        mainTimeText.text = "Tid : 0 minutter"
        mainIncrementText.text = "Tillegg : 0 sekunder"

        startButton.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java).apply {
                putExtra("time", timeCount)
                putExtra("increment", incrementCount)
            }
            startActivity(intent)
        }

        competitorButton.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }

        mainTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                mainTimeText.text = "Tid : $i minutter"
                timeCount = i;
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        mainIncrement.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                mainIncrementText.text = "Tillegg : $i sekunder"
                incrementCount = i;
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        blitzButton.setOnClickListener {
            timeCount = 3;
            incrementCount= 2;
            mainTime.progress = timeCount
            mainIncrement.progress = incrementCount
            mainTimeText.text = "Tid : $timeCount minutter"
            mainIncrementText.text = "Tillegg : $incrementCount sekunder"
        }

        rapidButton.setOnClickListener {
            timeCount = 15;
            incrementCount= 10;
            mainTime.progress = timeCount
            mainIncrement.progress = incrementCount
            mainTimeText.text = "Tid : $timeCount minutter"
            mainIncrementText.text = "Tillegg : $incrementCount sekunder"
        }

        classicButton.setOnClickListener {
            timeCount = 60;
            incrementCount= 20;
            mainTime.progress = timeCount
            mainIncrement.progress = incrementCount
            mainTimeText.text = "Tid : $timeCount minutter"
            mainIncrementText.text = "Tillegg : $incrementCount sekunder"
        }

        lardalgatenButton.setOnClickListener {
            timeCount = 10;
            incrementCount= 5;
            mainTime.progress = timeCount
            mainIncrement.progress = incrementCount
            mainTimeText.text = "Tid : $timeCount minutter"
            mainIncrementText.text = "Tillegg : $incrementCount sekunder"
        }
    }
}