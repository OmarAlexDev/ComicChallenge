package com.example.comicstore

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("https://gateway.marvel.com:443/v1/public/comics?format=comic&formatType=comic&apikey=9c40ff86cfd00882d81233dc85a10052")
    suspend fun getComics() : Response<Results>
}