package com.team11.genshinhelper

class Common {
    public enum class Element(val id: Int, val layout: Int) {
        Anemo(
            0,
            R.layout.card_element_anemo,
        ),
        Cryo(
            1,
            R.layout.card_element_cryo,
        ),
        Dendro(
            2,
            R.layout.card_element_dendro,
        ),
        Electro(
            3,
            R.layout.card_element_electro,
        ),
        Geo(
            4,
            R.layout.card_element_geo,
        ),
        Hydro(
            5,
            R.layout.card_element_hydro,
        ),
        Pyro(
            6,
            R.layout.card_element_pyro,
        )
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
    }

}
