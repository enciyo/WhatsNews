package com.example.whatsnews.model

data class BaseRoot(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)