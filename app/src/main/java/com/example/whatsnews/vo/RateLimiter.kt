package com.example.whatsnews.vo

import android.annotation.TargetApi
import android.os.Build
import android.os.SystemClock
import android.util.ArrayMap
import java.util.concurrent.TimeUnit

@TargetApi(Build.VERSION_CODES.KITKAT)
class RateLimiter<in Key>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = ArrayMap<Key, Long>()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key:Key) :Boolean{
        val lastFetched = timestamps[key]
        val now=  now()
        if(lastFetched==null){
            timestamps[key] = now
            return true
        }
        if(now- lastFetched>timeout){
            timestamps[key]=now
            return true
        }
        return false
    }
    private fun now() = SystemClock.uptimeMillis()

    @Synchronized
    fun reset(key: Key){
        timestamps.remove(key)
    }

}