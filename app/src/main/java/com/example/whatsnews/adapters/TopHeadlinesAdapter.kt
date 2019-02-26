package com.example.whatsnews.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsnews.R
import com.example.whatsnews.model.Article
import kotlinx.android.synthetic.main.item_tophead.view.*



class TopHeadlinesAdapter(val data:MutableList<Article>) : RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tophead,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun addData(list: List<Article>) {
        data.addAll(list)
        notifyItemRangeInserted(list.size - list.size - 1, list.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(data[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var binding: com.example.whatsnews.databinding.ItemTopheadBinding
        fun initView(article: Article) {
            binding= DataBindingUtil.bind(itemView)!!
            binding.article=article
            Glide.with(itemView).load(article.urlToImage).into(itemView.itemView)
            itemView.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("url",article.url)
                Navigation.findNavController(itemView).navigate(R.id.toWebView,bundle)
            }
        }
    }
}