package com.example.whatsnews.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.whatsnews.model.BaseRoot

@Dao
interface NewsDao{

    @Query("SELECT * FROM News")
    fun getTopHeadlines() : LiveData<BaseRoot>

    @Insert
    fun insert(data:BaseRoot)

    @Update
    fun update(data:BaseRoot)

}