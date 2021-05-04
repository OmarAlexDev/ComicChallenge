package com.example.comicstore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Results(
    var results:Comics?
)

data class Comics(
    var comics:List<Comic>?
)

@Parcelize
data class Comic(val picture:Int, val title:String, val issueNumber:String, val description:String, val pageCount:String) :
    Parcelable
