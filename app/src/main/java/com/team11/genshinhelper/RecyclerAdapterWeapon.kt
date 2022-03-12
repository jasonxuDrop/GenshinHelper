package com.team11.genshinhelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team11.genshinhelper.Common.*

class RecyclerAdapterWeapon : RecyclerView.Adapter<RecyclerAdapterWeapon.ViewHolder>() {

    // PLACEHOLDER VALUES
    private var titles = arrayOf(
        "Sword1",
        "Sword2",
        "Sword3",
        "Sword4",
        "Sword5",
        "Sword6",
        "Sword7",
        "Sword8",
        "Sword9isalongword",
        "Sword10 and extra words",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterWeapon.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_weapon, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterWeapon.ViewHolder, position: Int) {
        holder.title.text = titles[position] // TODO set item name
        holder.image.setImageResource(R.drawable.example_weapon_polar_star) // TODO set item image
        holder.imageBackground.setImageResource(Common.backgroundImages[position%5]) // TODO set star/rarity background (ie. 3 star item => Common.backgroundImages[3-1])
        if (holder.starStubInflated == null) {
            holder.starStub.layoutResource = Common.starLayout[position % 5] // TODO set star/rarity (ie. 3 star item => starLayout[3-1])
            holder.starStubInflated = holder.starStub.inflate()
        }
////        NOT APPLICABLE FOR WEAPONS
//        if (holder.elementStubInflated == null){
//            holder.elementStub.layoutResource = Element.Geo.layout // TODO set star/rarity (ie. Electro element => Element.Electro.layout)
//            holder.elementStubInflated = holder.elementStub.inflate()
//        }
        println("TODO populate with database data")
    }

    override fun getItemCount(): Int {
        println("TODO need to get the Item Count from database")
        return titles.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.CardItem)
        var imageBackground: ImageView = itemView.findViewById(R.id.CardImageBackground)
        var title : TextView = itemView.findViewById(R.id.CardTitle)
        var starStub : ViewStub = itemView.findViewById(R.id.CardStarsStub)
        var starStubInflated : View? = null
////        NOT APPLICABLE FOR WEAPONS
//        var elementStub : ViewStub = itemView.findViewById(R.id.CardElementStub)
//        var elementStubInflated : View? = null


        init {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailCharacterActivity::class.java)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}