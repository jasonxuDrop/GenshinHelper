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

class RecyclerAdapterConsumable : RecyclerView.Adapter<RecyclerAdapterConsumable.ViewHolder>() {

    // PLACEHOLDER VALUES
    private val titles = arrayOf(
        "Consumable 1",
        "Consumable 2",
        "Consumable 3",
        "Consumable 4",
        "Consumable 5",
        "Consumable 6",
        "Consumable 7",
        "Consumable 8",
        "Consumable 9",
        "Consumable 10",
        "Consumable 11 has a longer name",
        "Consumable 12",
        "Consumable 13",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterConsumable.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_consumable, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterConsumable.ViewHolder, position: Int) {
        holder.title.text = titles[position] // TODO set item name
        holder.subtitle.text = "Something" // TODO set secondary information
        holder.image.setImageResource(R.drawable.example_item_crab_ham_veggie_bake) // TODO set item image
        holder.imageBackground.setImageResource(Common.backgroundImages[position%5]) // TODO set star/rarity background (ie. 3 star item => Common.backgroundImages[3-1])
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
        var subtitle : TextView = itemView.findViewById(R.id.CardSubtitle)
        var image: ImageView = itemView.findViewById(R.id.CardItem)
        var imageBackground: ImageView = itemView.findViewById(R.id.CardImageBackground)
        var starStub : ViewStub = itemView.findViewById(R.id.CardStarsStub)
        var starStubInflated : View? = null

        init {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailConsumableActivity::class.java)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}