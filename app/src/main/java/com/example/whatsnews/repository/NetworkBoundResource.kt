package com.example.whatsnews.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.whatsnews.api.ApiEmptyResponse
import com.example.whatsnews.api.ApiResponse
import com.example.whatsnews.api.ApiSuccessResponse
import com.example.whatsnews.vo.Resource
import javax.xml.transform.Result

abstract class NetworkBoundResource<ResultType, RequestType> {

    var result = MediatorLiveData<Resource<RequestType>>()

    init {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    setValue(Resource.succes(response.body))
                }
            }

        }
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }


    fun asLiveData() = result as LiveData<Resource<RequestType>>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

}
