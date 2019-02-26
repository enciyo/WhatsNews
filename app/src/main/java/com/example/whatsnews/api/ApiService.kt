package com.example.whatsnews.api

import androidx.lifecycle.LiveData
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET(" v2/top-headlines")
    fun getTopHeadlines(@Query("apiKey") apiKey:String,
                        @Query("country") country:String) : LiveData<ApiResponse<TopHeadlineModel>>

    @GET("v2/everything")
    fun getEverything(@Query("apiKey") apiKey: String,
                      @Query("q") query:String,
                      @Query("language") language:String) : LiveData<ApiResponse<EverythingModel>>


}