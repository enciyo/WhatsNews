package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.model.BaseRoot
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository
@Inject constructor(private val service: ApiService)
{

    fun getTopHeadlines() : LiveData<Resource<BaseRoot>> {
        return object : NetworkBoundResource<BaseRoot,BaseRoot>() {

            override fun createCall(): LiveData<ApiResponse<BaseRoot>> {
                return service.getTopHeadlines(Ext.API_KEY,"tr")
            }

        }.asLiveData()

    }
}
