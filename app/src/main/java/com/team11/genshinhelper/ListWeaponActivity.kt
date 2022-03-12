package com.team11.genshinhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListWeaponActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterArtifact: RecyclerView.Adapter<RecyclerAdapterArtifact.ViewHolder>? = null
    private var adapterCharacter: RecyclerView.Adapter<RecyclerAdapterCharacter.ViewHolder>? = null
    private var adapterConsumable: RecyclerView.Adapter<RecyclerAdapterConsumable.ViewHolder>? = null
    private var adapterEnemy: RecyclerView.Adapter<RecyclerAdapterEnemy.ViewHolder>? = null
    private var adapterMaterial: RecyclerView.Adapter<RecyclerAdapterMaterial.ViewHolder>? = null
    private var adapterWeapon: RecyclerView.Adapter<RecyclerAdapterWeapon.ViewHolder>? = null
    private var selectedAdapter : RecyclerView.Adapter<out RecyclerView.ViewHolder>? = null
    private lateinit var recyclerView : RecyclerView
    lateinit var adapters : Array<RecyclerView.Adapter<out RecyclerView.ViewHolder>?>

    lateinit var option: Spinner
    lateinit var result: TextView


    private val options = arrayOf("Characters", "Materials", "Consumables", "Weapons", "Artifacts", "Enemies")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weapon_list)

        option = findViewById<Spinner>(R.id.spnOption)
        //result = findViewById<TextView>(R.id.spnText)


        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //result.text = options.get(position)
                selectedAdapter = adapters[position]
                recyclerView.adapter = selectedAdapter
                Log.d("List Changed", "To " + options[position])
                if (selectedAdapter != adapters[position]) {
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //result.text = "Please select an option"
            }
        }

        findViewById<ImageButton>(R.id.ResinNav).setOnClickListener{
            val intent = Intent(this, ResinTimerActivity::class.java)
            startActivity(intent)
        }

//        findViewById<ImageButton>(R.id.SearchNav).setOnClickListener{
//            //val intentStringValue: String = "List" + result.text + "Activity"
//            if(result.text == "Characters") {
//                val intent = Intent(this, ListCharacterActivity::class.java)
//                startActivity(intent)
//            }
//            else if(result.text == "Materials") {
//                val intent = Intent(this, ListMaterialActivity::class.java)
//                startActivity(intent)
//            }
//            else if(result.text == "Consumables") {
//                val intent = Intent(this, ListConsumableActivity::class.java)
//                startActivity(intent)
//            }
//            else if(result.text == "Weapons") {
//                val intent = Intent(this, ListWeaponActivity::class.java)
//                startActivity(intent)
//            }
//            else if(result.text == "Artifacts") {
//                val intent = Intent(this, ListArtifactActivity::class.java)
//                startActivity(intent)
//            }
//            else if(result.text == "Enemies") {
//                val intent = Intent(this, ListEnemyActivity::class.java)
//                startActivity(intent)
//            }
//            else {
//
//            }
//        }

        // RECYCLER VIEW STUFF
        recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        layoutManager = GridLayoutManager(this, 3)
        adapterArtifact = RecyclerAdapterArtifact()
        adapterCharacter = RecyclerAdapterCharacter()
        adapterConsumable = RecyclerAdapterConsumable()
        adapterEnemy = RecyclerAdapterEnemy()
        adapterMaterial = RecyclerAdapterMaterial()
        adapterWeapon = RecyclerAdapterWeapon()
        adapters = arrayOf(adapterCharacter, adapterMaterial, adapterConsumable, adapterWeapon, adapterArtifact, adapterEnemy)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterEnemy
    }
}