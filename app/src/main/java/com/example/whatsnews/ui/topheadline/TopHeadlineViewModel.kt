package com.example.whatsnews.ui.topheadline

import androidx.lifecycle.*
import com.example.whatsnews.db.EverythingDao
import com.example.whatsnews.db.NewsDao
import com.example.whatsnews.model.Article
import com.example.whatsnews.model.EverythingModel
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.repository.EverythingRepository
import com.example.whatsnews.repository.TopHeadlineRepository
import com.example.whatsnews.vo.Resource
import com.example.whatsnews.vo.Status
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val everythingRepository: EverythingRepository
) : ViewModel() {


    val getTopHeadline = topHeadlineRepository.getTopHeadlines()

    val getEverything = everythingRepository.getEverything()


}
