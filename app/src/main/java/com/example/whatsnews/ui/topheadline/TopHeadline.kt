package com.example.whatsnews.ui.topheadline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.whatsnews.adapters.TopHeadlinesAdapter
import com.example.whatsnews.R
import com.example.whatsnews.adapters.EverythingAdapter
import com.example.whatsnews.databinding.TopHeadlineFragmentBinding
import com.example.whatsnews.di.Injectable
import com.example.whatsnews.util.Ext
import com.example.whatsnews.viewmodel.WhatsNewsViewModelFactory
import com.example.whatsnews.vo.Status
import kotlinx.android.synthetic.main.top_headline_fragment.*
import javax.inject.Inject

class TopHeadline : Fragment(), Injectable, SwipeRefreshLayout.OnRefreshListener, LifecycleOwner {

    fun isRefreshing(boolean: Boolean) {
        refreshLayout.isRefreshing = boolean
    }


    override fun onRefresh() {
        Ext.i("OnReflesh")
        recyclerView.scrollToPosition(0)
        isRefreshing(false)
    }


    @Inject
    lateinit var viewFactory: WhatsNewsViewModelFactory
    private lateinit var viewModel: TopHeadlineViewModel

    var topHeadlinesAdapter = TopHeadlinesAdapter(mutableListOf())
    var everythingAdapter = EverythingAdapter(mutableListOf())


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
            it.data?.articles?.let { it1 ->
                if(topHeadlinesAdapter.data!=it1){
                    topHeadlinesAdapter.addData(it1)
                }
                isRefreshing(false)
            }

        })
        viewModel.getEverything.observe(this, Observer {
            it.data?.articles?.let { it1 ->
                if(everythingAdapter.data!=it1) {
                    everythingAdapter.addData(it1)
                }
            }

        })
    }


    fun setupRecyclerViewAndAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(view!!.context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = topHeadlinesAdapter
        recylerView2.layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)
        recylerView2.adapter = everythingAdapter
        isRefreshing(false)
    }


}
