package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.db.EverythingDao
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EverythingRepository @Inject constructor(private val service: ApiService,private val dao: EverythingDao){


    fun getEverything() : LiveData<Resource<EverythingModel>> {
        return object : NetworkBoundResource<EverythingModel,EverythingModel>(){
            override fun createCall(): LiveData<ApiResponse<EverythingModel>> {
                return service.getEverything(Ext.API_KEY,"turkey","tr")
            }

            override fun saveResult(data: EverythingModel) {
                dao.insert(data)
            }

            override fun shouldFetch(data: EverythingModel?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<EverythingModel> {
                return dao.getEverything()
            }

        }.asLiveData()
    }
}