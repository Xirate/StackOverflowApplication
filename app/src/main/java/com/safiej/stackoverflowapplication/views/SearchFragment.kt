package com.safiej.stackoverflowapplication.views

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.network.Rest
import com.safiej.stackoverflowapplication.network.responses.SearchResponse
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }

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
        titleTextView = view.findViewById(R.id.fragment_search_title)
        submitButton = view.findViewById(R.id.fragment_search_submit)
        input = view.findViewById(R.id.fragment_search_input)
    }

    private fun setListeners() {
        submitButton.setOnClickListener {
            Rest.soApi.getResultsFor(query = input.text.toString()).enqueue(object :Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    Toast.makeText(context, "Success. ${response.body()?.resultList?.size} results.", Toast.LENGTH_LONG).show()
                }
            })
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