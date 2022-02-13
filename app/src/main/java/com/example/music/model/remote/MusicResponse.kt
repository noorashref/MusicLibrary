package com.example.music.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicResponse(
    val resultCount: Int,
    val results: List<MusicItem>
) : Parcelable

@Parcelize
data class MusicItem(
    val artistName: String,
    val artworkUrl60: String,
    val collectionName: String,
    val trackPrice: Double,
    val previewUrl :String
    ) : Parcelable