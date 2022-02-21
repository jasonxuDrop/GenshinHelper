package com.team11.genshinhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ListConsumableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list)

        findViewById<ImageButton>(R.id.ResinNav).setOnClickListener{
            val intent = Intent(this, ResinTimerActivity::class.java)
            startActivity(intent)
        }
    }
}