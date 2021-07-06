package com.example.glowroadtest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glowroadtest.network.ApiCallBack
import com.example.glowroadtest.data.ImageDetails
import com.example.glowroadtest.data.Photo
import com.example.glowroadtest.repository.ImageDetailsRepository

class ImageItemViewModel : ViewModel() {
    private val imageRepository = ImageDetailsRepository()
    var liveImageData = MutableLiveData<List<Photo>>()
    var pageChange = MutableLiveData<Int>()

    fun fetchData(id: Int) {
        imageRepository.getImageDetails(object : ApiCallBack<ImageDetails> {
            override fun onError() {
                Log.e("ImageItemViewModel", "API FAILED")
            }

            override fun onSuccess(list: ImageDetails?) {
                list?.let {
                    list.photos.photo.let {
                        liveImageData.postValue(it)
                    }
                }
            }
        }, id.toString())
    }
}