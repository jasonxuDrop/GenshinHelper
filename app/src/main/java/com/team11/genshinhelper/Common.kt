package com.team11.genshinhelper

class Common {
    public enum class Element(val id: Int) {
        Anemo(0), Cryo(1), Dendro(2), Electro(3), Geo(4), Hydro(5), Pyro(6)
    }

    companion object {

        // COMMON CARD ELEMENTS
        val backgroundImages = arrayOf(
            R.drawable.card_background_1_star,
            R.drawable.card_background_2_star,
            R.drawable.card_background_3_star,
            R.drawable.card_background_4_star,
            R.drawable.card_background_5_star,
        )
        val starLayout = arrayOf(
            R.layout.card_stars_1,
            R.layout.card_stars_2,
            R.layout.card_stars_3,
            R.layout.card_stars_4,
            R.layout.card_stars_5,
        )
        val elementLayout = arrayOf(
            R.layout.card_element_anemo,
            R.layout.card_element_cryo,
            R.layout.card_element_dendro,
            R.layout.card_element_electro,
            R.layout.card_element_geo,
            R.layout.card_element_hydro,
            R.layout.card_element_pyro,
        )

        public enum class Element {
            Anemo, Cryo, Dendro, Electro, Geo, Hydro, Pyro
        }
    }

}
