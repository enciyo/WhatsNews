package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whatsnews.AppExecutors
import com.example.whatsnews.adapters.DataChanged
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.db.EverythingDao
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.RateLimiter
import com.example.whatsnews.vo.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EverythingRepository @Inject constructor(private val service: ApiService,private val dao: EverythingDao,private val appExecutors: AppExecutors){


    private val rateLimiter = RateLimiter<String>(3,TimeUnit.SECONDS)
    fun getEverything() : MutableLiveData<Resource<EverythingModel>> {
        return object : NetworkBoundResource<EverythingModel,EverythingModel>(appExecutors){
            override fun createCall(): LiveData<ApiResponse<EverythingModel>> {
                return service.getEverything(Ext.API_KEY,"spor","tr")
            }

            override fun saveResult(data: EverythingModel) {
                dao.insert(data)
            }

            override fun shouldFetch(data: EverythingModel?): Boolean {
                return data==null || rateLimiter.shouldFetch(data.status)
            }

            override fun loadFromDb(): LiveData<EverythingModel> {
                return dao.getEverything()
            }

        }.asLiveData()
    }
}