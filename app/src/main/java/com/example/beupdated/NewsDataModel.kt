package com.example.beupdated

data class NewsDataModel(
    val articles: List<ArticlesDataModel>,
    val status: String,
    val totalResults: Int
)