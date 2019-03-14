package com.example.whatsnews.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.whatsnews.db.converters.ArticleConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Everything")
@TypeConverters(ArticleConverter::class)
class EverythingModel(
    @PrimaryKey(autoGenerate = true)
    var pKey: Int = 0,
    var articles: List<Article> = emptyList(),
    var status: String,
    var totalResults: Int
)
