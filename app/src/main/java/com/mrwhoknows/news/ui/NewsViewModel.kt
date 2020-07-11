package com.mrwhoknows.news.ui

import androidx.lifecycle.ViewModel
import com.mrwhoknows.news.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}