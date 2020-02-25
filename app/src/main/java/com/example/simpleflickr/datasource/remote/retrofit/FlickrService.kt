package com.example.simpleflickr.datasource.remote.retrofit

import retrofit2.http.GET

interface FlickrService {

    @GET("api/")
    fun getImageData(

    )
}