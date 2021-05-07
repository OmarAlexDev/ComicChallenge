package com.example.comicstore

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("comics?apikey=c5548dfe1a70d91017ddc19cf9aeed25&ts=0&hash=eaec9b45c371f5848420ae5d4c55dbce")
    suspend fun getComics() : Response<Results>
}