package com.example.repository

import com.example.apiinterface.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getAlbumList() = apiInterface.getPhotos()
}