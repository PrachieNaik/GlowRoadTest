package com.example.glowroadtest.network

interface ApiCallBack<T> {
    fun onSuccess(list: T?)
    fun onError()
}