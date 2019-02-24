package com.example.whatsnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.whatsnews.di.DaggerAppComponent
import com.example.whatsnews.model.BaseModel
import com.example.whatsnews.repository.TopHeadlineRepository
import com.example.whatsnews.vo.Resource
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(repository: TopHeadlineRepository) : ViewModel() {



    val getTopHeadlines : LiveData<Resource<BaseModel>> = repository.getTopHeadlines()


}
