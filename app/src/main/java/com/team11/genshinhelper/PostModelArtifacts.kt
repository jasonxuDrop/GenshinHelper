package com.team11.genshinhelper

import com.google.gson.annotations.SerializedName

class PostModelArtifacts {
//character details
    var name: String? = null
    var max_rarity: Int? = null
    @SerializedName("2-piece_bonus")
    var twoPieceBonus: String? = null
    @SerializedName("4-piece_bonus")
    var fourPieceBonus: String? = null
}