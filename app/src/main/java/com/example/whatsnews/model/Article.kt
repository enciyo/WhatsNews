package com.example.whatsnews.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


data class Article(
    val author: String = " ",
    val content: String = " ",
    val description: String = " ",
    val publishedAt: String = " ",
    @Ignore
    val source: Source,
    val title: String = " ",
    val url: String = " ",
    val urlToImage: String = " "
)