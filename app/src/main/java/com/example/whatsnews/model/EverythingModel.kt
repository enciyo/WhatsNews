package com.example.whatsnews.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.whatsnews.db.converters.ArticleConverter

@Entity(tableName = "Everything")
@TypeConverters(ArticleConverter::class)
class EverythingModel(
    @PrimaryKey(autoGenerate = true)
    var pKey: Int = 0,
    @get:Bindable
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
) : BaseObservable()
