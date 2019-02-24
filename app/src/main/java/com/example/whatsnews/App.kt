package com.example.whatsnews

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import com.example.whatsnews.di.AppComponent
import com.example.whatsnews.di.DaggerAppComponent

@SuppressLint("Registered")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        getComponent().inject(this)
    }


    fun getComponent() : AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}