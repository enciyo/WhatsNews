package com.example.whatsnews.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.whatsnews.api.ApiEmptyResponse
import com.example.whatsnews.api.ApiErrorResponse
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiSuccessResponse
import com.example.whatsnews.vo.Resource
import javax.xml.transform.Result

abstract class NetworkBoundResource<ResultType, RequestType> {

    var result = MediatorLiveData<Resource<ResultType>>()

    init {
        val dbSource = loadFromDb()
        result.addSource(dbSource){data->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchDataFromNetwork(dbSource)
            }else {
                result.addSource(dbSource){newData->
                    setValue(Resource.succes(newData))
                }
            }
        }

    }

    private fun fetchDataFromNetwork(dbSource:LiveData<ResultType>){
        val apiResponse= createCall()
        result.addSource(dbSource){data->
            setValue(Resource.loading(data))
        }
        result.addSource(apiResponse){response->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response){
                is ApiSuccessResponse -> {
                    saveResult(response.body)
                    result.addSource(loadFromDb()){
                        Resource.succes(it)
                    }
                }
                is ApiEmptyResponse -> {
                    result.addSource(loadFromDb()){
                        Resource.succes(it)
                    }
                }

                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource){
                        Resource.error(response.msg,it)
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


    fun asLiveData() = result as LiveData<Resource<ResultType>>
    protected open fun onFetchFailed() {}

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    protected abstract fun saveResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?) : Boolean

    @MainThread
    protected abstract fun loadFromDb() : LiveData<ResultType>

}
