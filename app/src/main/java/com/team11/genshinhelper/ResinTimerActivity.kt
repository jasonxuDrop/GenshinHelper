package com.team11.genshinhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ResinTimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resin_timer)

        val door = 3

        findViewById<ImageButton>(R.id.EditButton).setOnClickListener{
            val intent = Intent(this, ResinTimerEditActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.SearchNav).setOnClickListener{
            val intent = Intent(this, ListActivityMain::class.java)
            startActivity(intent)
        }
    }
}