package com.example.whatsnews.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsnews.Adapter
import com.example.whatsnews.App
import com.example.whatsnews.R
import com.example.whatsnews.di.Injectable
import com.example.whatsnews.model.Article
import com.example.whatsnews.viewmodel.WhatsNewsViewModelFactory
import javax.inject.Inject

class TopHeadline : Fragment(),Injectable{

    @Inject
    lateinit var viewFactory: WhatsNewsViewModelFactory

    private lateinit var viewModel: TopHeadlineViewModel
    lateinit var adapter: Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_headline_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerViewAndAdapter()
        viewModel = ViewModelProviders.of(this, viewFactory).get(TopHeadlineViewModel::class.java)
        viewModel.getTopHeadlines.observe(this, Observer {
            if(it.data!=null){
                initView(it.data!!.articles)
            }
        })
    }

    fun setupRecyclerViewAndAdapter() {
        adapter= Adapter(mutableListOf())
        val lmanager= LinearLayoutManager(view!!.context,RecyclerView.VERTICAL,false)
        val recyclerView:RecyclerView = view!!.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=lmanager
        recyclerView.adapter=adapter

    }

    private fun initView(data: List<Article>) {
        adapter.addData(data)

    }

}
