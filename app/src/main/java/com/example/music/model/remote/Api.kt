package com.example.music.model.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search")
    fun getMusicList(
        @Query(TERM) term: String,
        @Query(MEDIA) media : String,
        @Query(ENTITY) Entity: String,
        @Query(LIMIT) limit: String
    ):Call<MusicResponse>


    companion object{
        fun initRetrofit() : Api{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}
