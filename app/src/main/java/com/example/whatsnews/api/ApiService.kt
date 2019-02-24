package com.example.whatsnews.api

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.whatsnews.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET(" v2/top-headlines")
    fun getTopHeadlines(@Query("apiKey") apiKey:String,
                        @Query("country") country:String) : LiveData<ApiResponse<BaseModel>>
}