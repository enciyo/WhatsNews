package com.example.whatsnews.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.whatsnews.db.converters.ArticleConverter

@Entity(tableName = "Everything")
@TypeConverters(ArticleConverter::class)
data class EverythingModel(
    @PrimaryKey(autoGenerate = true)
    val pKey: Int = 0,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
