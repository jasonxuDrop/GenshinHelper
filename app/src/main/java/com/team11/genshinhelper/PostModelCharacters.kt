package com.team11.genshinhelper

class PostModelCharacters {
//character details
    var name:String? = null
    var vision:String? = null
    var weapon:String? = null
    var nation: String? = null
    var affiliation: String? = null
    var rarity: String? = null
    var constellation: String? = null
    var birthday: String? = null
    var description: String? = null
    //skillTalents is an array of objects with the properties "name" "unlock" "description" "type"
    var skillTalents: Array<SKILLTALENTS>? = null
    class SKILLTALENTS {
        var name: String? = null
        var unlock: String? = null
        var description: String? = null
        var type: String? = null
    }
    //passiveTalents in an array of objects with the properties "name" "unlock" "description" "level"
    var passive: Array<PASSIVETALENTS>? = null
    class PASSIVETALENTS {
        var name: String? = null
        var unlock: String? = null
        var description: String? = null
        var level: Int? = null
    }
    //constellations is an array of objects with the properties "name" "unlock" "description" "level"
    var constellations: Array<CONSTELLATIONS>? = null
    class CONSTELLATIONS {
        var name: String? = null
        var unlock: String? = null
        var description: String? = null
        var level: Int? = null
    }
    var vision_key: String? = null
    var weapon_type: String? = null
}