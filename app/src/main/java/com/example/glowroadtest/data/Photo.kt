package com.example.glowroadtest.data

data class Photo (
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: Int,
    var title: String
) {
    fun getUrl() : String {
        return  "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}_m.jpg"
    }
}