package com.safiej.stackoverflowapplication.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.data.model.Question
import com.safiej.stackoverflowapplication.data.repository.QuestionRepository
import com.safiej.stackoverflowapplication.views.NavigationCallback
import com.safiej.stackoverflowapplication.views.adapters.SearchResultsAdapter

class ResultsFragment : Fragment() {

    companion object {

        const val KEY_QUERY = "KEY_QUERY"

        fun newInstance(query: String): ResultsFragment {
            return ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_QUERY, query)
                }
            }
        }
    }

    private lateinit var navigationCallback: NavigationCallback

    private lateinit var searchInput: EditText
    private lateinit var searchButton: ImageView
    private lateinit var resultsList: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var resultsAdapter: SearchResultsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        val query = arguments?.getString(KEY_QUERY) ?: ""

        findViews(view)
        setupViews(query)
        setListeners()
        executeRequest()

        return view
    }

    private fun findViews(view: View) {
        searchInput = view.findViewById(R.id.fragment_results_input)
        searchButton = view.findViewById(R.id.fragment_results_submit)
        resultsList = view.findViewById(R.id.fragment_results_list)
        swipeRefreshLayout = view.findViewById(R.id.fragment_results_swipe_refresh)
    }

    private fun setupViews(query: String) {
        searchInput.setText(query, TextView.BufferType.EDITABLE)
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        resultsAdapter = SearchResultsAdapter(navigationCallback)
        resultsList.adapter = resultsAdapter
        resultsList.layoutManager = LinearLayoutManager(context)
    }

    private fun setListeners() {
        searchButton.setOnClickListener {
            executeRequest()
        }
        swipeRefreshLayout.setOnRefreshListener {
            executeRequest()
        }
    }

    private fun executeRequest() {
        swipeRefreshLayout.isRefreshing = true
        val input = searchInput.text.toString()
        QuestionRepository.getQuestionsContaining(input) {
            onRequestFinished(it)
            arguments?.putString(KEY_QUERY, input)
        }
    }

    private fun onRequestFinished(questionList: ArrayList<Question>?) {
        swipeRefreshLayout.isRefreshing = false
        if (questionList == null) {
            return
        }
        resultsAdapter.setData(questionList)
    }

    fun setNavigationCallback(navigationCallback: NavigationCallback) {
        this.navigationCallback = navigationCallback
    }
}