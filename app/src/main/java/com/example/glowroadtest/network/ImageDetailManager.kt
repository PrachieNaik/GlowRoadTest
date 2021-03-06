package com.example.glowroadtest.network

import android.util.Log
import com.example.glowroadtest.data.ImageDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ImageDetailManager {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/services/rest/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private var fetchApi: ImageDetailApi = retrofit.create(ImageDetailApi::class.java)

    fun getImageData(apiCallBack: ApiCallBack<ImageDetails>, id: String) {
        val url = if (id == "1") {
            "https://kamateraho.s3.ap-south-1.amazonaws.com/test_response.json"
        } else {
            "?method=flickr.photos.search&api_key=641c87bd78e54920b01e9a5d8bb726d7&format=json&nojsoncallback=1&extras=url_q&text=polo&page=$id"
        }
        val call: Call<ImageDetails> = fetchApi.getImageDetailsList(url)
        call.enqueue(object : Callback<ImageDetails>
        {
            override fun onFailure(call: Call<ImageDetails>?, t: Throwable?) {
                t?.printStackTrace()
                Log.e("ImageDetailsManager", "API FAILED")
            }

            override fun onResponse(call: Call<ImageDetails>?, response: Response<ImageDetails>?) {
                if (!response!!.isSuccessful) {
                    Log.e("ImageDetailsManager", response.toString())
                } else {
                    apiCallBack.onSuccess(response.body())
                }
            }
        })
    }
}
