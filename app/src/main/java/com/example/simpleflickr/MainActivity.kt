package com.example.simpleflickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.simpleflickr.datasource.remote.retrofit.RetrofitHelper
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View){
        when(view.id){
            R.id.btnSearch -> {
                executeAsyncRetrofitCall()
            }
        }
    }

    fun executeAsyncRetrofitCall() {
        val retrofitHelper = RetrofitHelper()
        //retrofitHelper.getFlickrCallService(cacheDir)         // not using caches
    }

 //   @Subscribe(threadMode = ThreadMode.MAIN){}
//    fun onTagResponse(tagResponse: TagResponse) {
//
//    }

}
