package com.team11.genshinhelper

import com.google.gson.annotations.SerializedName

class PostModelEnemies {
    //character details
    var id: String? = null
    var name: String? = null
    var description: String? = null
    var region: String? = null
    var type: String? = null
    var family: String? = null
    var faction: String? = null
    //elements is an array of strings
    var elements: Array<String?>? = null
    //drops is an array of DROPS objects with the parameters "name", "rarity", and "minimum-level"
    var drops: Array<DROPS>? = null
    class DROPS {
        var name: String? = null
        var rarity: Int? = null
        @SerializedName ("minimum-level")
        var minLvl: Int? = null
    }
}