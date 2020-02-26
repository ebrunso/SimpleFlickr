package com.example.simpleflickr.datasource.remote.okhttp

import android.util.Log
import com.example.simpleflickr.datasource.remote.BASE_URL_FLICKR_TAG
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit

class OkHttpHelper() {

    fun getClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
        return okHttpClient
    }

    fun makeAsyncApiCall(url : String){
        val request = Request.Builder().url(url).build()
        getClient().newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if(url.equals(BASE_URL_FLICKR_TAG)){
                    val json = response.body?.string()
                    val userResults = Gson().fromJson<TagResponse>(json, TagResponse::class.java)
                    EventBus.getDefault().post(userResults)

            }

            override fun onFailure(call: Call, e: IOException) {
                makeSyncApiCall(BASE_URL_FLICKR_TAG)
                Log.e("ERROR_TAG", "Error in makeAsyncApiCall ---->" ,e)

            }
        })
    }

    fun makeSyncApiCall(url : String) : String{
        val request = Request
            .Builder()
            .url(url)
            .cacheControl(CacheControl.Builder().maxAge(30, TimeUnit.SECONDS).build())
            .build()
        val response = getClient().newCall(request).execute()
        val json = response.body!!.string()
        Log.d("TAG", json)
        return json
    }
}