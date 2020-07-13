package com.mrwhoknows.news.repository

import com.mrwhoknows.news.api.RetrofitInstance
import com.mrwhoknows.news.db.ArticleDatabase
import com.mrwhoknows.news.model.Article

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNo: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNo)

    suspend fun searchNews(searchQuery: String, pageNo: Int) =
        RetrofitInstance.api.serachForNews(searchQuery, pageNo)

    suspend fun upsert(article: Article) = db.getArticleDao().insertUpdate(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}