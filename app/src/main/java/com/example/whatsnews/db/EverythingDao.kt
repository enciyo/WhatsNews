package com.example.whatsnews.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whatsnews.model.EverythingModel

@Dao
interface EverythingDao {

    @Query("SELECT * FROM  Everything")
    fun getEverything() : LiveData<EverythingModel>


    @Insert
    fun insert(data: EverythingModel)
}