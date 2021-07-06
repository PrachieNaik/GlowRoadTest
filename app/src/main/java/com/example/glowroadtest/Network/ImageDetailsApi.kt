package com.example.glowroadtest.Network

import com.example.glowroadtest.data.ImageDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageDetailsApi {
    @GET
    fun getImageDetailsList(@Url url:String): Call<ImageDetails>
}