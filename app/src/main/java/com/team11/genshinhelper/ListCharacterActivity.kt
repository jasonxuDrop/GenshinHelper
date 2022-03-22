package com.team11.genshinhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        findViewById<ImageButton>(R.id.ResinNav).setOnClickListener{
            val intent = Intent(this, ResinTimerActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.SearchNav).setOnClickListener{
            //val intentStringValue: String = "List" + result.text + "Activity"
            if(result.text == "Characters") {
                val intent = Intent(this, ListCharacterActivity::class.java)
                startActivity(intent)
            }
            if(result.text == "Materials") {
                val intent = Intent(this, ListMaterialActivity::class.java)
                startActivity(intent)
            }
            if(result.text == "Consumables") {
                val intent = Intent(this, ListConsumableActivity::class.java)
                startActivity(intent)
            }
            if(result.text == "Weapons") {
                val intent = Intent(this, ListWeaponActivity::class.java)
                startActivity(intent)
            }
            if(result.text == "Artifacts") {
                val intent = Intent(this, ListArtifactActivity::class.java)
                startActivity(intent)
            }
            if(result.text == "Enemies") {
                val intent = Intent(this, ListEnemyActivity::class.java)
                startActivity(intent)
            }
            else {

            }
        }
    }

}