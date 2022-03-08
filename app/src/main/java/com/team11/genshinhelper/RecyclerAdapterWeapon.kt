package com.team11.genshinhelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterWeapon : RecyclerView.Adapter<RecyclerAdapterWeapon.ViewHolderWeapon>() {

    // COMMON CARD ELEMENTS
    private var backgroundImages = arrayOf(
        R.drawable.card_background_1_star,
        R.drawable.card_background_2_star,
        R.drawable.card_background_3_star,
        R.drawable.card_background_4_star,
        R.drawable.card_background_5_star,
    )
    private var starLayout = arrayOf(
        R.layout.card_stars_1,
        R.layout.card_stars_2,
        R.layout.card_stars_3,
        R.layout.card_stars_4,
        R.layout.card_stars_5,
    )
    private var elementLayout = arrayOf(
        R.layout.card_element_anemo,
        R.layout.card_element_cryo,
        R.layout.card_element_dendro,
        R.layout.card_element_electro,
        R.layout.card_element_geo,
        R.layout.card_element_hydro,
        R.layout.card_element_pyro,
    )

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterWeapon.ViewHolderWeapon {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_weapon, parent, false)
        return ViewHolderWeapon(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterWeapon.ViewHolderWeapon, position: Int) {
        holder.title.text = titles[position]
        holder.imageBackground.setImageResource(backgroundImages[position%5])
        holder.image.setImageResource(R.drawable.example_weapon_polar_star)
        holder.starStub.layoutResource = starLayout[position%5]//[]3-1]
        val starViewInflated: View = holder.starStub.inflate()
        println("TODO populate with database data")
    }

    override fun getItemCount(): Int {
        println("TODO need to get the Item Count from database")
        return titles.size
    }


    inner class ViewHolderWeapon(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var imageBackground: ImageView
        var title : TextView
        var star1 : ImageView
        var starStub : ViewStub


        init {
            image = itemView.findViewById(R.id.CardItem)
            imageBackground = itemView.findViewById(R.id.CardImageBackground)
            title = itemView.findViewById(R.id.CardTitle)
            star1 = itemView.findViewById(R.id.CardRarityStar1)
            starStub = itemView.findViewById(R.id.CardStarsStub)

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailCharacterActivity::class.java)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}