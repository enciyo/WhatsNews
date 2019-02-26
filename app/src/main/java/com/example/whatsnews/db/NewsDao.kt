package com.example.whatsnews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel

@Dao
interface NewsDao{

    @Query("SELECT * FROM News")
    fun getTopHeadlines() : LiveData<TopHeadlineModel>

    @Insert
    fun insert(data:TopHeadlineModel)

    @Update
    fun update(data:TopHeadlineModel)

}