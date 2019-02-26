package com.example.whatsnews.model

import androidx.room.Embedded


data class Article(
    val author: String = " ",
    val content: String = " ",
    val description: String = " ",
    val publishedAt: String = " ",
    @Embedded
    val source: Source,
    val title: String = " ",
    val url: String = " ",
    val urlToImage: String = " "
)