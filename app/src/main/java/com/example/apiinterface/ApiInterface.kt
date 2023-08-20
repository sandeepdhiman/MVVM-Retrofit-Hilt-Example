package com.example.apiinterface

import com.example.model.Album
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/photos")
    suspend fun getPhotos(): Response<List<Album>>
}