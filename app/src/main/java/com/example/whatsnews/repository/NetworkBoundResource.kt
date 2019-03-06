package com.example.whatsnews.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.whatsnews.AppExecutors
import com.example.whatsnews.api.ApiEmptyResponse
import com.example.whatsnews.api.ApiErrorResponse
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiSuccessResponse
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.Resource
import timber.log.Timber
import javax.xml.transform.Result

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    var result = MediatorLiveData<Resource<ResultType>>()

    init {
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchDataFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    Ext.i("LoadFromDb")
                    setValue(Resource.succes(newData))
                }
            }
        }

    }

    private fun fetchDataFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { data ->
            setValue(Resource.loading(data))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        Ext.i(response.body.toString())
                        saveResult(response.body)
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDb()) {
                                Resource.succes(it)
                            }

                        }
                    }


                }
                is ApiEmptyResponse -> {
                    Ext.i("ApiEmptyResponse")
                    result.addSource(loadFromDb()) {
                        Resource.succes(it)
                    }
                }

                is ApiErrorResponse -> {
                    Ext.i("ApiErrorResponse")
                    onFetchFailed()
                    result.addSource(dbSource) {
                        Resource.error(response.msg, it)
                    }
                }
            }
        }


    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }


    fun asLiveData() = result as MutableLiveData<Resource<ResultType>>
    protected open fun onFetchFailed() {}

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    protected abstract fun saveResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

}
