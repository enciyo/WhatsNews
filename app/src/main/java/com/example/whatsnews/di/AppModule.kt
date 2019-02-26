package com.example.whatsnews.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.db.EverythingDao
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.db.NewsDatabase
import com.example.whatsnews.vo.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            application,
            NewsDatabase::class.java,
            "blaa")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Singleton
    @Provides
    fun provideDao(db:NewsDatabase) : NewsDao{
        return db.newsDao()
    }

    @Singleton
    @Provides
    fun provideEverythingDao(db:NewsDatabase) : EverythingDao{
        return db.everythingDao()
    }



}