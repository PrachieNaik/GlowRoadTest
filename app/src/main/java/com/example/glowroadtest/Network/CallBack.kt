package com.example.glowroadtest.Network

interface CallBack<T> {
    fun onSuccess(list: T?)
    fun onError()
}