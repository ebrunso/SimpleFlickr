package com.example.simpleflickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.simpleflickr.datasource.remote.retrofit.RetrofitHelper
import org.greenrobot.eventbus.EventBus
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
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


   // @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onTagResponse(tagResponse: TagResponse) {
//
//    }

}
