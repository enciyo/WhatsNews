package com.example.whatsnews.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whatsnews.App
import com.example.whatsnews.R
import com.example.whatsnews.model.BaseModel
import com.example.whatsnews.viewmodel.WhatsNewsViewModelFactory
import kotlinx.android.synthetic.main.top_headline_fragment.*
import javax.inject.Inject

class TopHeadline : Fragment() {

    @Inject
    lateinit var viewFactory: WhatsNewsViewModelFactory

    private lateinit var viewModel: TopHeadlineViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_headline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App().getComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewFactory).get(TopHeadlineViewModel::class.java)
        viewModel.getTopHeadlines.observe(this, Observer {
            initView(it.data)
            Log.i("MyLogger", it.data?.articles?.get(2)?.author)
        })
    }

    private fun initView(data: BaseModel?) {
       for (i in 0 until data!!.articles.size){
           Log.i("MyLogger",data.articles[i].title)
       }
    }

}
