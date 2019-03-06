package com.example.whatsnews.ui.topheadline

import androidx.lifecycle.*
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.repository.EverythingRepository
import com.example.whatsnews.repository.TopHeadlineRepository
import com.example.whatsnews.util.Ext
import com.example.whatsnews.vo.AbsentLiveData
import com.example.whatsnews.vo.Resource
import com.example.whatsnews.vo.Status
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val everythingRepository: EverythingRepository
) : ViewModel() {

    val topHeadline = Transformations.switchMap(topHeadlineRepository.getTopHeadlines()) {
        if (it.data?.articles.isNullOrEmpty()){
            Ext.i("Null")
            return@switchMap topHeadlineRepository.getTopHeadlines()
        }
        return@switchMap topHeadlineRepository.getTopHeadlines()
    }
    val everything = Transformations.switchMap(everythingRepository.getEverything()) {
        if(it.data?.articles.isNullOrEmpty()){
            AbsentLiveData.create<Resource<EverythingModel>>()
        }
        return@switchMap everythingRepository.getEverything()
    }
}
