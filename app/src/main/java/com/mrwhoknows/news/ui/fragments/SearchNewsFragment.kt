package com.mrwhoknows.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mrwhoknows.news.NewsActivity
import com.mrwhoknows.news.R
import com.mrwhoknows.news.ui.NewsViewModel

class SearchNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_news, container, false)

        viewModel = (activity as NewsActivity).viewModel
    }
}