package com.example.comicstore

data class ComicCarrito(val id: String, val title: String, val issueNumber: String, val pageCount: String, val prices: String, val thumbnail: String){
    constructor():this("", "", "","","", "")
}