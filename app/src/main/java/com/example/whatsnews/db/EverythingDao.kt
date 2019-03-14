package com.example.whatsnews.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.whatsnews.model.Article
import com.example.whatsnews.model.EverythingModel

@Dao
interface EverythingDao {

    @Query("SELECT * FROM  Everything order by pKey")
    fun getEverything() : LiveData<EverythingModel>


    @Query("SELECT * FROM  Everything order by pKey")
    fun getForCheck() : EverythingModel

    @Query("DELETE FROM Everything")
    fun deletAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: EverythingModel)

    @Delete
    fun delete(data: EverythingModel)

}