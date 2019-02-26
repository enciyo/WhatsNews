package com.example.whatsnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.repository.EverythingRepository
import com.example.whatsnews.repository.TopHeadlineRepository
import com.example.whatsnews.vo.Resource
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(topHeadlineRepository: TopHeadlineRepository,getEverythingRepository: EverythingRepository) : ViewModel() {



    val getTopHeadlines : LiveData<Resource<TopHeadlineModel>> = topHeadlineRepository.getTopHeadlines()

    val getEverything : LiveData<Resource<EverythingModel>> = getEverythingRepository.getEverything()


}
