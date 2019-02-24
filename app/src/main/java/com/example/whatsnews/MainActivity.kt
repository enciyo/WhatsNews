package com.example.whatsnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.whatsnews.api.*
import com.example.whatsnews.di.DaggerAppComponent
import com.example.whatsnews.repository.TopHeadlineRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
