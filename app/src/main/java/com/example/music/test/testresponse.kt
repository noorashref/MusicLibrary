package com.example.music.test


import com.google.gson.annotations.SerializedName

data class testresponse(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Result>
)