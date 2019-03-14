package com.example.whatsnews.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.whatsnews.model.Article
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel

@Dao
interface NewsDao{

    @Query("SELECT * FROM News order by pKey DESC")
    fun getTopHeadlines() : LiveData<TopHeadlineModel>

    @Query("SELECT * FROM News order by pKey DESC")
    fun getForCheck() : TopHeadlineModel

    @Query("DELETE FROM News")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data:TopHeadlineModel)

    @Delete
    fun delete(data:TopHeadlineModel)

}