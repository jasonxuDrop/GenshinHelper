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

class RecyclerAdapterEnemy : RecyclerView.Adapter<RecyclerAdapterEnemy.ViewHolder>() {

    enum class EnemyType(val text : String) {
        WeeklyBoss (
            "Weekly Boss",
                ),
        NormalBoss (
            "Normal Boss",
                ),
        Elite (
            "Elite",
                ),
        Common (
            "Common",
                ),
        Wildlife (
            "Wildlife",
                ),
    }
    // PLACEHOLDER VALUES
    private val titles = arrayOf(
        "Enemy 1",
        "Enemy 2",
        "Enemy 3",
        "Enemy 4",
        "Enemy 5",
        "Enemy 6",
        "Enemy 7",
        "Enemy 8",
        "Enemy 9",
        "Enemy 10",
        "Enemy 11 has a longer name",
        "Enemy 12",
        "Enemy 13",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterEnemy.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_template_enemy, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterEnemy.ViewHolder, position: Int) {
        holder.title.text = titles[position] // TODO set item name
        holder.subtitle.text = EnemyType.Elite.text // TODO set enemy type (replace "Elite" with WeeklyBoss, NormalBoss, Common or Wildlife)
        holder.image.setImageResource(R.drawable.example_enemy_pyro_abyss_mage_icon) // TODO set item image
        holder.imageBackground.setImageResource(Common.backgroundImages[position%5]) // TODO set star/rarity background (ie. 3 star item => Common.backgroundImages[3-1])
        if (holder.elementStubInflated == null){
            holder.elementStub.layoutResource = Element.Geo.layout // TODO set star/rarity (ie. Electro element => Element.Electro.layout)
            holder.elementStubInflated = holder.elementStub.inflate()
        }
        println("TODO populate with database data")
    }

    override fun getItemCount(): Int {
        println("TODO need to get the Item Count from database")
        return titles.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.CardTitle)
        var subtitle : TextView = itemView.findViewById(R.id.CardSubtitle)
        var image: ImageView = itemView.findViewById(R.id.CardItem)
        var imageBackground: ImageView = itemView.findViewById(R.id.CardImageBackground)
        var elementStub : ViewStub = itemView.findViewById(R.id.CardElementStub)
        var elementStubInflated : View? = null
////        NOT APPLICABLE
//        var starStub : ViewStub = itemView.findViewById(R.id.CardStarsStub)
//        var starStubInflated : View? = null

        init {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailEnemyActivity::class.java)
                intent.putExtra("itemName", title.text)

                itemView.context.startActivity(intent)
            }
        }
    }
}