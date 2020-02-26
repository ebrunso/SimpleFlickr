package com.example.simpleflickr.datasource.remote.retrofit

import android.util.Log
import com.example.simpleflickr.datasource.remote.BASE_URL_CHUCK_NORRIS
import com.example.simpleflickr.datasource.remote.BASE_URL_FLICKR_TAG
import com.example.simpleflickr.datasource.remote.retrofit.FlickrService.Companion.getFlickrCallService
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import com.example.simpleflickr.datasource.remote.okhttp.OkHttpHelper

class RetrofitHelper {

    fun getRetrofitInstance(): Retrofit {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpHelper().getClient())
        builder.baseUrl(BASE_URL_FLICKR_TAG)
        return builder.build()
    }

    fun getRandomUserCallService() =
        getRetrofitInstance().create(FlickrService::class.java)


    fun startChuckNorrisRequest() {
        getFlickrCallService()
            .getTagData("random")
            .enqueue(object : Callback<FlickrService> {
                override fun onResponse(call: Call<FlickrService>, response: Response<FlickrService>) {
                    EventBus.getDefault().post(response.body())
                }

                override fun onFailure(call: Call<FlickrService>, t: Throwable) {
                    Log.e("TAG", "ERROR IN RETROFIT -->", t)
                }
            })
    }

}