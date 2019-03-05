package com.example.whatsnews.ui.topheadline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsnews.adapters.TopHeadlinesAdapter
import com.example.whatsnews.R
import com.example.whatsnews.adapters.EverythingAdapter
import com.example.whatsnews.di.Injectable
import com.example.whatsnews.model.Article
import com.example.whatsnews.util.Ext
import com.example.whatsnews.viewmodel.WhatsNewsViewModelFactory
import javax.inject.Inject

class TopHeadline : Fragment(), Injectable {

    @Inject
    lateinit var viewFactory: WhatsNewsViewModelFactory

    private lateinit var viewModel: TopHeadlineViewModel

    lateinit var topHeadlinesAdapter: TopHeadlinesAdapter
    lateinit var everythingAdapter: EverythingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_headline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerViewAndAdapter()
        viewModel = ViewModelProviders.of(this, viewFactory).get(TopHeadlineViewModel::class.java)
        viewModel.getTopHeadlines.observe(this, Observer {
            if (it.data != null) {
                initView(it.data!!.articles)
            }
        })

        viewModel.getEverything.observe(this, Observer {
            if (it.data != null) {
                everythingAdapter.addData(it.data!!.articles)
            }
        })



    }

    fun setupRecyclerViewAndAdapter() {
        topHeadlinesAdapter = TopHeadlinesAdapter(mutableListOf())
        everythingAdapter = EverythingAdapter(mutableListOf())
        val lmanager = LinearLayoutManager(view!!.context, RecyclerView.HORIZONTAL, false)
        val lmanager2 = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recyclerView)
        val recylerView2: RecyclerView = view!!.findViewById(R.id.recylerView2)
        recyclerView.layoutManager = lmanager
        recyclerView.adapter = topHeadlinesAdapter
        recylerView2.layoutManager = lmanager2
        recylerView2.adapter = everythingAdapter


    }

    private fun initView(data: List<Article>) {
        Ext.i(data.toString())
        topHeadlinesAdapter.addData(data)

    }

}