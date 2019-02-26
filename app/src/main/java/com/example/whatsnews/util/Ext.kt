package com.example.whatsnews.util

import timber.log.Timber

object Ext {
    val BASE_URL = "https://newsapi.org/"
    val API_KEY = "548a48ee94b3421a900177a1de1dad7d"

    fun i(msg:String){
        Timber.i("MyLog" + msg.toString() )
    }

}