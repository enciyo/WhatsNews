package com.example.whatsnews.ui.topheadline

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.whatsnews.adapters.TopHeadlinesAdapter
import com.example.whatsnews.R
import com.example.whatsnews.adapters.DataChanged
import com.example.whatsnews.adapters.EverythingAdapter
import com.example.whatsnews.databinding.TopHeadlineFragmentBinding
import com.example.whatsnews.di.Injectable
import com.example.whatsnews.model.Article
import com.example.whatsnews.model.TopHeadlineModel
import com.example.whatsnews.util.Ext
import com.example.whatsnews.viewmodel.WhatsNewsViewModelFactory
import com.example.whatsnews.vo.Resource
import com.example.whatsnews.vo.Status
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.top_headline_fragment.*
import javax.inject.Inject

class TopHeadline : Fragment(), Injectable, SwipeRefreshLayout.OnRefreshListener, LifecycleOwner, DataChanged {
    override fun onDataChanged() {

    }

    override fun onRefresh() {
        Ext.i("OnReflesh")
        viewModelStore.clear()
        fetchData()
        refreshLayout.isRefreshing = false
        setupRecyclerViewAndAdapter()
    }


    @Inject
    lateinit var viewFactory: WhatsNewsViewModelFactory


    private lateinit var viewModel: TopHeadlineViewModel


    var topHeadlinesAdapter = TopHeadlinesAdapter(mutableListOf(), this)
    var everythingAdapter = EverythingAdapter(mutableListOf(), this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TopHeadlineFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.top_headline_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewFactory).get(TopHeadlineViewModel::class.java)
        setupRecyclerViewAndAdapter()
        fetchData()
        refreshLayout.setOnRefreshListener(this)

    }

    fun fetchData() {


        viewModel.getTopHeadline.observe(this, Observer {
            if (!it.data?.articles.isNullOrEmpty()){
                topHeadlinesAdapter.addData(it.data!!.articles)
            }

        })
        viewModel.getEverything.observe(this, Observer {
          if(!it.data?.articles.isNullOrEmpty()){
              everythingAdapter.addData(it.data!!.articles)
          }

        })
    }


    fun setupRecyclerViewAndAdapter() {
        val lmanager = LinearLayoutManager(view!!.context, RecyclerView.HORIZONTAL, false)
        val lmanager2 = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recyclerView)
        val recylerView2: RecyclerView = view!!.findViewById(R.id.recylerView2)
        recyclerView.layoutManager = lmanager
        recyclerView.adapter = topHeadlinesAdapter
        recylerView2.layoutManager = lmanager2
        recylerView2.adapter = everythingAdapter
        refreshLayout.isRefreshing = false
    }


}
