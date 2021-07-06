package com.example.glowroadtest.repository

import com.example.glowroadtest.network.ApiCallBack
import com.example.glowroadtest.network.ImageDetailManager
import com.example.glowroadtest.data.ImageDetails

class ImageDetailsRepository {

    private val imageManager = ImageDetailManager

    fun getImageDetails(callback: ApiCallBack<ImageDetails>, id: String) {
        imageManager.getImageData(callback, id)
    }
}