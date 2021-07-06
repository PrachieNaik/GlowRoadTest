package com.example.glowroadtest.Network

import android.util.Log
import com.example.glowroadtest.data.ImageDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ImageDetailsManager {

    var retrofit: Retrofit
    private var fetchApi: ImageDetailsApi

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/rest/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        fetchApi = retrofit.create(ImageDetailsApi::class.java)
        Log.e("IM", "init")
    }

    fun getImageData(callBack: CallBack<ImageDetails>, id: String) {
        var url: String? = null
        url = if (id == "1") {
            "https://kamateraho.s3.ap-south-1.amazonaws.com/test_response.json"
        } else {
            "?method=flickr.photos.search&api_key=641c87bd78e54920b01e9a5d8bb726d7&format=json&nojsoncallback=1&extras=url_q&text=polo&page=$id"

        }
        val call: Call<ImageDetails> = fetchApi.getImageDetailsList(url)
        call.enqueue(object : Callback<ImageDetails>       //will be executed on different code
        {
            override fun onFailure(call: Call<ImageDetails>?, t: Throwable?) {
                t?.printStackTrace()
                Log.e("IM", "onFailure")
            }

            override fun onResponse(call: Call<ImageDetails>?, response: Response<ImageDetails>?) {
                if (!response!!.isSuccessful) {
                    Log.e("EventManager", response.toString())
                }
                callBack.onSuccess(response.body())

            }

        })

    }

}
