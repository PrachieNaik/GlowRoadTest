package com.example.glowroadtest.network

import com.example.glowroadtest.data.ImageDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageDetailApi {
    @GET
    fun getImageDetailsList(@Url url:String): Call<ImageDetails>
}