package com.safiej.stackoverflowapplication.views

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.transition.AutoTransition
import android.transition.Scene
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.model.Question
import com.safiej.stackoverflowapplication.network.Rest
import com.safiej.stackoverflowapplication.network.responses.SearchResponse
import com.safiej.stackoverflowapplication.views.adapters.FragmentType
import com.safiej.stackoverflowapplication.views.adapters.SearchResultsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }

    private lateinit var logoImage: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var submitButton: ImageView
    private lateinit var input: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        findViews(view)
        setTitle()
        setListeners()
        return view
    }

    private fun findViews(view: View) {
        logoImage = view.findViewById(R.id.fragment_search_logo)
        titleTextView = view.findViewById(R.id.fragment_search_title)
        submitButton = view.findViewById(R.id.fragment_search_submit)
        input = view.findViewById(R.id.fragment_search_input)
    }

    private fun setListeners() {
        submitButton.setOnClickListener {
            Navigator.requestNavigation(FragmentType.RESULTS, input.text.toString())
        }
    }

    private fun setTitle() {
        val spannable = SpannableStringBuilder().append("Stack")
        val start = spannable.length
        spannable.append("Overflow")
        spannable.setSpan(StyleSpan(Typeface.BOLD), start, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleTextView.setText(spannable, TextView.BufferType.SPANNABLE)
    }
}