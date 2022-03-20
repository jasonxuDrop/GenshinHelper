package com.team11.genshinhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

import android.widget.ArrayAdapter




class ListActivityMain : AppCompatActivity() {

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
        setContentView(R.layout.main_list)

        option = findViewById<Spinner>(R.id.spnOption)
        //result = findViewById<TextView>(R.id.spnText)


        option.setPopupBackgroundResource(R.drawable.spinner_bg_blue)
        var spinnerDropdown = ArrayAdapter<String>(this, R.layout.spinner_item, options)
        spinnerDropdown.setDropDownViewResource(R.layout.spinner_item_dropdown)
        option.adapter = spinnerDropdown

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (selectedAdapter != adapters[position]) {
                    selectedAdapter = adapters[position]
                    recyclerView.adapter = selectedAdapter
                    Log.d("List Changed", "To " + options[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //result.text = "Please select an option"
            }
        }

        findViewById<LinearLayout>(R.id.ResinNav).setOnClickListener{
            val intent = Intent(this, ResinTimerActivity::class.java)
            startActivity(intent)
        }

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