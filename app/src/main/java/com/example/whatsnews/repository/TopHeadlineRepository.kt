package com.example.whatsnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.whatsnews.AppExecutors
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiService
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.AbsentLiveData
import com.example.whatsnews.vo.RateLimiter
import com.example.whatsnews.vo.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository
@Inject constructor(private val service: ApiService, private val dao: NewsDao, private val appExecutors: AppExecutors) {

    private val rateLimiter = RateLimiter<String>(3, TimeUnit.SECONDS)

    fun getTopHeadlines(): LiveData<Resource<TopHeadlineModel>> {
        return object : NetworkBoundResource<TopHeadlineModel, TopHeadlineModel>(appExecutors) {
            override fun removeDb() {
                dao.deleteAll()
            }

            override fun saveResult(data: TopHeadlineModel) {

                    appExecutors.diskIO().execute {
                        Ext.i("Worked Insert")
                        dao.insert(data)
                }

            }

            override fun shouldFetch(data: TopHeadlineModel?): Boolean {
                return data == null || rateLimiter.shouldFetch(data.status)
            }

            override fun loadFromDb(): LiveData<TopHeadlineModel> {
                return dao.getTopHeadlines()
            }

            override fun createCall(): LiveData<ApiResponse<TopHeadlineModel>> {
                return service.getTopHeadlines(Ext.API_KEY, "tr")
            }

        }.asLiveData()

    }
}
