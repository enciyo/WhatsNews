package com.example.whatsnews.model

import androidx.room.*
import com.example.whatsnews.db.converters.ArticleConverter


@Entity(tableName = "News")
@TypeConverters(ArticleConverter::class)
data class BaseRoot(
    @PrimaryKey(autoGenerate = true)
    val pKey: Int = 0,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
