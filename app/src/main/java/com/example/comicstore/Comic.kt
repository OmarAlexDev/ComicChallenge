package com.example.comicstore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(val picture:Int, val modelo:String, val marca:String, val anio:String) :
    Parcelable
