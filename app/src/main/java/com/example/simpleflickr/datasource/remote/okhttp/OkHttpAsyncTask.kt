package com.example.simpleflickr.datasource.remote.okhttp

import android.os.AsyncTask
import com.example.simpleflickr.datasource.remote.BASE_URL_FLICKR_TAG
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.lang.Exception

class OkHttpAsyncTask() : AsyncTask<Void, Void, String>() {
    lateinit var tagResponse: TagResponse
    override fun doInBackground(vararg p0: Void?): String {
        try {
            val okHttpHelper = OkHttpHelper()
            val randomUserURL = "https://randomuser.me/api/?results=10"
            val json = okHttpHelper.makeSyncApiCall(randomUserURL)
            tagResponse = Gson().fromJson<TagResponse>(json, TagResponse::class.java)
            return json
        } catch (e : Exception) {
            val okHttpHelper = OkHttpHelper()
            val json = okHttpHelper.makeSyncApiCall(BASE_URL_FLICKR_TAG)
            return json
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(::tagResponse.isInitialized) {
            EventBus.getDefault().post(tagResponse)
        } else {
            val jokeResponse = Gson().fromJson<TagResponse>(result, TagResponse::class.java)
            EventBus.getDefault().post(jokeResponse)
        }
    }


}