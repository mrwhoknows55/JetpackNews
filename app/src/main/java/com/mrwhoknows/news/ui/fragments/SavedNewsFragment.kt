package com.mrwhoknows.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrwhoknows.news.ui.NewsActivity
import com.mrwhoknows.news.R
import com.mrwhoknows.news.adapters.NewsAdapter
import com.mrwhoknows.news.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
//        setupRecyclerView()
//        newsAdapter.setOnItemClickListener { clickedArticle ->
//
//            val bundle = Bundle().apply {
//                putSerializable("article", clickedArticle)
//            }
//            findNavController().navigate(
////                BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(clickedArticle)
//                R.id.action_savedNewsFragment_to_articleFragment,
//                bundle
//            )
//        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}