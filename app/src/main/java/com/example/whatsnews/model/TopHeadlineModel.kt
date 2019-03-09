package com.example.whatsnews.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.whatsnews.db.converters.ArticleConverter


@Entity(tableName = "News")
@TypeConverters(ArticleConverter::class)
data class TopHeadlineModel(
    @PrimaryKey(autoGenerate = true)
    var pKey: Int = 0,
    @get:Bindable
    var articles:List<Article>,
    var status: String,
    var totalResults: Int
): BaseObservable()
