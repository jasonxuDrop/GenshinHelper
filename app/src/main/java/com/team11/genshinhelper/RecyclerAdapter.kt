package com.team11.genshinhelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf(
        "Sword1",
        "Sword2",
        "Sword3",
        "Sword4",
        "Sword5",
        "Sword6",
        "Sword7",
        "Sword8",
        "Sword9",
        "Sword10",
    )
    private var backgroundImages = arrayOf(
        R.drawable.card_background_1_star,
        R.drawable.card_background_2_star,
        R.drawable.card_background_3_star,
        R.drawable.card_background_4_star,
        R.drawable.card_background_5_star,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_weapon, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.title.text = titles[position]
        holder.imageBackgroud.setImageResource(backgroundImages[position%5])
        holder.image.setImageResource(R.drawable.example_weapon_polar_star)
        println("TODO populate with database data")
    }

    override fun getItemCount(): Int {
        println("TODO need to get the Item Count from database")
        return titles.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var imageBackgroud: ImageView
        var title : TextView
        var star1 : ImageView


        init {
            image = itemView.findViewById(R.id.CardItem)
            imageBackgroud = itemView.findViewById(R.id.CardImageBackground)
            title = itemView.findViewById(R.id.CardTitle)
            star1 = itemView.findViewById(R.id.CardRarityStar1)

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailCharacterActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }
}