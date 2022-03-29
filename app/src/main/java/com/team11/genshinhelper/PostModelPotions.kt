package com.team11.genshinhelper

import com.google.gson.annotations.SerializedName

class PostModelPotions {
    //character details
    var name: String? = null
    var effect: String? = null
    var rarity: Int? = null
    //crating is an array of objects with the parameters "item", "quantity"
    var crafting: Array<CRAFTING>? = null
    class CRAFTING {
        var item: String? = null
        var quantity: Int? = null
    }
}