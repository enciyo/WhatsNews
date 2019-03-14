package com.example.whatsnews.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsnews.R
import com.example.whatsnews.databinding.ItemTopheadBinding
import com.example.whatsnews.model.Article
import com.example.whatsnews.util.Ext
import kotlinx.android.synthetic.main.item_tophead.view.*


class TopHeadlinesAdapter(val data: MutableList<Article>) :
    RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTopheadBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tophead, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(list: List<Article>) {
        Ext.i("Data: " + list.toString())
        data.addAll(list)
        notifyItemRangeInserted(list.size - list.size - 1, list.size)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(data[position])
    }

    inner class ViewHolder(private val binding: ItemTopheadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initView(article: Article) {
            binding.article = article
            binding.setVariable(BR.article, article)
            binding.executePendingBindings()
            Glide.with(itemView).load(article.urlToImage).into(itemView.itemView)
            itemView.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("url", article.url)
                Navigation.findNavController(itemView).navigate(R.id.toWebView, bundle)

            }
        }
    }
}