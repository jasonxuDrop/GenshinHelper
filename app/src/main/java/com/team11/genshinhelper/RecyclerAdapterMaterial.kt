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

class RecyclerAdapterMaterial : RecyclerView.Adapter<RecyclerAdapterMaterial.ViewHolder>() {

    // PLACEHOLDER VALUES
    private val titles = arrayOf(
        "Material 1",
        "Material 2",
        "Material 3",
        "Material 4",
        "Material 5",
        "Material 6",
        "Material 7",
        "Material 8",
        "Material 9",
        "Material 10",
        "Material 11 has a longer name",
        "Material 12",
        "Material 13",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterMaterial.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_material, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterMaterial.ViewHolder, position: Int) {
        holder.title.text = titles[position] // TODO set item name
        holder.subtitle.text = "Something" // TODO set secondary information
        holder.image.setImageResource(R.drawable.example_item_prithiva_topaz_gemstone) // TODO set item image
        holder.imageBackground.setImageResource(Common.backgroundImages[position%5]) // TODO set star/rarity background (ie. 3 star item => Common.backgroundImages[3-1])
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

        init {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailEnemyActivity::class.java)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}