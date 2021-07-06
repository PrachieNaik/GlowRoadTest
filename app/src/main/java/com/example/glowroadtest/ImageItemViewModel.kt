package com.example.glowroadtest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glowroadtest.Network.CallBack
import com.example.glowroadtest.data.ImageDetails
import com.example.glowroadtest.data.Photo

class ImageItemViewModel : ViewModel() {
    private val imageRepository = ImageDetailsRepository()
    var livePhotoUrls = MutableLiveData<List<Photo>>()
    var pageChange = MutableLiveData<Int>()

    fun fetchData(id: Int) {
        imageRepository.getImageDetails(object : CallBack<ImageDetails> {
            override fun onError() {
                Log.e("ImageItemViewModel", "onError")
            }

            override fun onSuccess(list: ImageDetails?) {
                list?.let {
                    list.photos.photo.let {
                        livePhotoUrls.postValue(it)
                       // photoUrls.addAll(it)
                    }
                    Log.e("ImageItemViewModel", "${livePhotoUrls.value}")
                }
            }
        }, id.toString())
    }
}