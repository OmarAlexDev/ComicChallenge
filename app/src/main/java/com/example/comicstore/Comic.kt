package com.example.comicstore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Results(
    val data: @RawValue Data
): Parcelable

data class Data(
    val results : MutableList<Comic>
)

data class Comic(
    val title: String,
    val issueNumber: Int,
    val description: String,
    val pageCount: Int,
    val prices: MutableList<Info>,
    val thumbnail: Thumbnail,
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Info(
    val price: Double
)