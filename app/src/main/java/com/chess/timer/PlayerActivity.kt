package com.chess.timer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val seierErlend : Button = findViewById(R.id.button);
        val seierSondre : Button = findViewById(R.id.button4);
        val seierNora : Button = findViewById(R.id.button7);

        val remisErlend : Button = findViewById(R.id.button2);
        val remisSondre : Button = findViewById(R.id.button5);
        val remisNora : Button = findViewById(R.id.button8);

        val tapErlend : Button = findViewById(R.id.button3);
        val tapSondre : Button = findViewById(R.id.button6);
        val tapNora : Button = findViewById(R.id.button9);

        val poengErlend : TextView = findViewById(R.id.textView2)
        val poengSondre : TextView = findViewById(R.id.textView4)
        val poengNora : TextView = findViewById(R.id.textView6)

        val sharedPref = this.getSharedPreferences("players", Context.MODE_PRIVATE)

2
       fun refreshTexts() {
           val erlendhighScore = sharedPref.getLong("erlend", 0)
           val sondrehighScore = sharedPref.getLong("sondre", 0)
           val norahighScore = sharedPref.getLong("nora", 0)

           poengErlend.text = "$erlendhighScore poeng"
           poengSondre.text = "$sondrehighScore poeng"
           poengNora.text = "$norahighScore poeng"
       }

        fun victory(key : String) {
            var highScore = sharedPref.getLong(key, 0)
            highScore+=2;
            with (sharedPref.edit()) {
                putLong(key, highScore)
                apply()
            }
            refreshTexts()
        }

        fun remis(key : String) {
            var highScore = sharedPref.getLong(key, 0)
            highScore+=1;
            with (sharedPref.edit()) {
                putLong(key, highScore)
                apply()
            }
            refreshTexts()
        }

        fun defeat(key : String) {
            var highScore = sharedPref.getLong(key, 0)
            if (highScore.toDouble() == 0.0) return;
            highScore-=1;
            with (sharedPref.edit()) {
                putLong(key, highScore)
                apply()
            }
            refreshTexts()
        }

        refreshTexts()

        seierErlend.setOnClickListener { victory("erlend") }
        seierSondre.setOnClickListener { victory("sondre") }
        seierNora.setOnClickListener { victory("nora") }

        remisErlend.setOnClickListener { remis("erlend") }
        remisSondre.setOnClickListener { remis("sondre") }
        remisNora.setOnClickListener { remis("nora") }

        tapErlend.setOnClickListener { defeat("erlend") }
        tapSondre.setOnClickListener { defeat("sondre") }
        tapNora.setOnClickListener { defeat("nora") }




    }
}