package com.example.glowroadtest

import android.util.Log
import com.example.glowroadtest.Network.CallBack
import com.example.glowroadtest.Network.ImageDetailsManager
import com.example.glowroadtest.data.ImageDetails

class ImageDetailsRepository {

    private val imageManager = ImageDetailsManager

    fun getImageDetails(callback: CallBack<ImageDetails>, id: String) {
        imageManager.getImageData(callback, id)
    }
}