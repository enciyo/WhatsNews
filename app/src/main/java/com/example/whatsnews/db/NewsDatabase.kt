package com.example.whatsnews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.repository.EverythingRepository

@Database(entities = [TopHeadlineModel::class,EverythingModel::class],version = 20,exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao() : NewsDao
    abstract fun everythingDao() : EverythingDao
}