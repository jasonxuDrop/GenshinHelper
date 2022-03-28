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
import kotlin.reflect.KClass

// TODO THIS RECYCLER VIEW IS USING CUSTOM ARRAYS FOR DATA, WOULD RECOMMEND ADDING API FOR OTHER RECYCLER LISTS FIRST @Paul
class RecyclerAdapterCharacter : RecyclerView.Adapter<RecyclerAdapterCharacter.ViewHolder>() {

    // PLACEHOLDER VALUES
    private val titles = arrayOf(
        "Diluc",
        "Klee",
        "Beidou",
    )
    private val thumbnails = arrayOf (
        R.drawable.example_character_diluc_thumb,
        R.drawable.example_character_klee_thumb,
        R.drawable.example_character_beidou_thumb,
    )
    private val backgrounds = arrayOf (
        Common.backgroundImages[4],
        Common.backgroundImages[4],
        Common.backgroundImages[3],
    )
    private val elementLayouts = arrayOf (
        Element.Pyro.layout,
        Element.Pyro.layout,
        Element.Electro.layout,
    )
    private val starLayouts = arrayOf (
        Common.starLayout[4],
        Common.starLayout[4],
        Common.starLayout[3],
    )
    private val detailActivities = arrayOf (
        DetailCharacterActivity::class.java,
        DetailCharacterActivity3::class.java,
        DetailCharacterActivity2::class.java,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCharacter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_character, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterCharacter.ViewHolder, position: Int) {
        holder.title.text = titles[position/4] // TODO set item name
        holder.image.setImageResource(thumbnails[position/4]) // TODO set item image
        holder.imageBackground.setImageResource(backgrounds[position/4]) // TODO set star/rarity background (ie. 3 star item => Common.backgroundImages[3-1])
        if (holder.elementStubInflated == null){
            holder.elementStub.layoutResource = elementLayouts[position/4] // TODO set star/rarity (ie. Electro element => Element.Electro.layout)
            holder.elementStubInflated = holder.elementStub.inflate()
        }
        if (holder.starStubInflated == null) {
            holder.starStub.layoutResource = starLayouts[position/4] // TODO set star/rarity (ie. 3 star item => starLayout[3-1])
            holder.starStubInflated = holder.starStub.inflate()
        }
        holder.setActivityToOpen(detailActivities[position/4])
        // TODO populate with database data
    }

    override fun getItemCount(): Int {
        // TODO need to get the Item Count from database
        return titles.size * 4
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
            // TODO Open detail activity and pass on extra data used to populate it
        }

        fun setActivityToOpen(activity : Class<*>) {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, activity)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}