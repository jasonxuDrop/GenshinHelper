package com.team11.genshinhelper

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @get:GET(value = "posts")
    val posts : Call<List<PostModel?>?>?

    companion object {
        //currently pulling character detail data for "amber"
        //const val BASE_URL = "'http://api.genshin.dev/characters/amber'"
       const val BASE_URL = "http://jsonplaceholder.typicode.com/"
    }
}