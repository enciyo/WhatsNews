package com.example.whatsnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsnews.model.Article
import kotlinx.android.synthetic.main.item_tophead.view.*

class Adapter(val list:List<Article>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tophead,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun initView(article: Article) {
            itemView.apply {
                adapter_item_title.text=article.title
                adapter_item_author.text=article.author
                adapter_item_content.text=article.content
            }

            Glide.with(itemView).load(article.urlToImage).into(itemView.adapter_item_image)
        }
    }
}