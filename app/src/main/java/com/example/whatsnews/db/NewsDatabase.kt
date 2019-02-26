package com.example.whatsnews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whatsnews.model.BaseRoot

@Database(entities = [BaseRoot::class],version = 1,exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao() : NewsDao
}