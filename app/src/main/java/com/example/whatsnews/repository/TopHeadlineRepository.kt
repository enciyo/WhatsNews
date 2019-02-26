package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.model.BaseRoot
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository
@Inject constructor(private val service: ApiService,private val dao: NewsDao)
{

    fun getTopHeadlines() : LiveData<Resource<BaseRoot>> {
        return object : NetworkBoundResource<BaseRoot,BaseRoot>() {
            override fun saveResult(data: BaseRoot) {
                dao.insert(data)
            }

            override fun shouldFetch(data: BaseRoot?): Boolean {
               return data==null || data.articles.isEmpty()
            }

            override fun loadFromDb(): LiveData<BaseRoot> {
                return dao.getTopHeadlines()
            }

            override fun createCall(): LiveData<ApiResponse<BaseRoot>> {
                return service.getTopHeadlines(Ext.API_KEY,"tr")
            }

        }.asLiveData()

    }
}
