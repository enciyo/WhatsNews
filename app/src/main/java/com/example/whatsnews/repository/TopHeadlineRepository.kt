package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.model.BaseModel
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.Resource
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository
@Inject constructor(private val service: ApiService)
{

    fun getTopHeadlines() : LiveData<Resource<BaseModel>> {
        return object : NetworkBoundResource<BaseModel,BaseModel>() {

            override fun createCall(): LiveData<ApiResponse<BaseModel>> {
                return service.getTopHeadlines(Ext.API_KEY,"tr")
            }

        }.asLiveData()

    }
}
