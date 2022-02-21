package com.team11.genshinhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ListCharacterActivity : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list)


        option = findViewById(R.id.spnOption) as Spinner
        result = findViewById(R.id.spnText) as TextView

        val options = arrayOf("Characters", "Materials", "Consumables", "Weapons", "Artifacts", "Enemies")

        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result.text = options.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Please select an option"
            }
        }

        findViewById<Button>(R.id.ResinNav).setOnClickListener{
            val intent = Intent(this, ResinTimerActivity::class.java)
            startActivity(intent)
        }

//      findViewById<ImageButton>(R.id.SearchNav).setOnClickListener{
//          val intent = Intent(this, ResinTimerEditActivity::class.java)
//          startActivity(intent)
//      }
    }
}