package com.mrwhoknows.news.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrwhoknows.news.ui.NewsActivity
import com.mrwhoknows.news.R
import com.mrwhoknows.news.adapters.NewsAdapter
import com.mrwhoknows.news.ui.NewsViewModel
import com.mrwhoknows.news.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.mrwhoknows.news.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "SearchNewsFragment"

class SearchNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        var job: Job? = null
        etSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                it?.let {
                    if (it.toString().isNotEmpty()) viewModel.searchNews(it.toString())
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error occurred $message ")
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })

        newsAdapter.setOnItemClickListener { clickedArticle ->

            val bundle = Bundle().apply {
                putSerializable("article", clickedArticle)
            }
            findNavController().navigate(
//                BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(clickedArticle)
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun hideProgressbar() {
        paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressbar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}