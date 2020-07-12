package com.mrwhoknows.news.repository

import com.mrwhoknows.news.api.RetrofitInstance
import com.mrwhoknows.news.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNo: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNo)

    suspend fun searchNews(searchQuery: String, pageNo: Int) =
        RetrofitInstance.api.serachForNews(searchQuery, pageNo)

}