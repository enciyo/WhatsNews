package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository
@Inject constructor(private val service: ApiService,private val dao: NewsDao)
{

    fun getTopHeadlines() : LiveData<Resource<TopHeadlineModel>> {
        return object : NetworkBoundResource<TopHeadlineModel,TopHeadlineModel>() {
            override fun saveResult(data: TopHeadlineModel) {
                dao.insert(data)
            }

            override fun shouldFetch(data: TopHeadlineModel?): Boolean {
               return true
            }

            override fun loadFromDb(): LiveData<TopHeadlineModel> {
                return dao.getTopHeadlines()
            }

            override fun createCall(): LiveData<ApiResponse<TopHeadlineModel>> {
                return service.getTopHeadlines(Ext.API_KEY,"tr")
            }

        }.asLiveData()

    }
}
