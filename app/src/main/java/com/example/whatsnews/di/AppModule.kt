package com.example.whatsnews.di

import androidx.lifecycle.ViewModel
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.vo.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule{

    @Singleton
    @Provides
    fun provideApiService() : ApiService{
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }




}