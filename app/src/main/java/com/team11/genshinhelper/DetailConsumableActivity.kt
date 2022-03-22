package com.team11.genshinhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailConsumableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consumable_detailed)


        var itemName = intent.getStringExtra("itemName")


        findViewById<TextView>(R.id.consumableName).text = itemName

    }
}