package com.example.whatsnews.model

import androidx.room.Entity

@Entity
data class Source(
    val id: Any,
    val name: String
)