package com.example.whatsnews.model

data class BaseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)