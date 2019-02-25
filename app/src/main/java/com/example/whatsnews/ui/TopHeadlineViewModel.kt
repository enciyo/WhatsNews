package com.example.whatsnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.whatsnews.model.BaseRoot
import com.example.whatsnews.repository.TopHeadlineRepository
import com.example.whatsnews.vo.Resource
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(repository: TopHeadlineRepository) : ViewModel() {



    val getTopHeadlines : LiveData<Resource<BaseRoot>> = repository.getTopHeadlines()


}
