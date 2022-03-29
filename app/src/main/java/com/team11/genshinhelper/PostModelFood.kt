package com.team11.genshinhelper

class PostModelFood {
//character details
    var name: String? = null
    var rarity: Int? = null
    var type: String? = null
    var effect: String? = null
    var hasRecipe: Boolean? = null
    var description: String? = null
    var proficiency: Int? = null
    //recipe is an array of objects with the properties "item", "quantity"
    var recipe: Array<RECIPE>? = null
    class RECIPE {
        var item: String? = null
        var quantity: Int? = null
    }
}