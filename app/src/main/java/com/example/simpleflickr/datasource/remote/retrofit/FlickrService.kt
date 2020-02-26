package com.example.simpleflickr.datasource.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

interface FlickrService {

    companion object{
        fun getFlickrCallService() =
            RetrofitHelper().getRetrofitInstance().create(FlickrService::class.java)
    }

    @GET("photo/{photos}")
    fun getTagData(@Path("photos") type: String)
            //: Call<TagResponse>
}