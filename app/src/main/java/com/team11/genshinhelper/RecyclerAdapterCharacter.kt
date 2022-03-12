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

class RecyclerAdapterCharacter : RecyclerView.Adapter<RecyclerAdapterCharacter.ViewHolder>() {

    // PLACEHOLDER VALUES
    private val titles = arrayOf(
        "Character 1",
        "Character 2",
        "Character 3",
        "Character 4",
        "Character 5",
        "Character 6",
        "Character 7",
        "Character 8",
        "Character 9",
        "Character 10",
        "Character 11 has a longer name",
        "Character 12",
        "Character 13",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCharacter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_character, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterCharacter.ViewHolder, position: Int) {
        holder.title.text = titles[position] // TODO set item name
        holder.image.setImageResource(R.drawable.example_character_beidou_thumb) // TODO set item image
        holder.imageBackground.setImageResource(Common.backgroundImages[position%5]) // TODO set star/rarity background (ie. 3 star item => Common.backgroundImages[3-1])
        if (holder.elementStubInflated == null){
            holder.elementStub.layoutResource = Element.Geo.layout // TODO set star/rarity (ie. Electro element => Element.Electro.layout)
            holder.elementStubInflated = holder.elementStub.inflate()
        }
        if (holder.starStubInflated == null) {
            holder.starStub.layoutResource = Common.starLayout[position % 5] // TODO set star/rarity (ie. 3 star item => starLayout[3-1])
            holder.starStubInflated = holder.starStub.inflate()
        }
        // TODO populate with database data
    }

    override fun getItemCount(): Int {
        // TODO need to get the Item Count from database
        return titles.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.CardTitle)
        var image: ImageView = itemView.findViewById(R.id.CardItem)
        var imageBackground: ImageView = itemView.findViewById(R.id.CardImageBackground)
        var elementStub : ViewStub = itemView.findViewById(R.id.CardElementStub)
        var elementStubInflated : View? = null
        var starStub : ViewStub = itemView.findViewById(R.id.CardStarsStub)
        var starStubInflated : View? = null

        init {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailCharacterActivity::class.java)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}