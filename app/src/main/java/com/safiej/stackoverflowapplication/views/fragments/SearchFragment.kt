package com.safiej.stackoverflowapplication.views.fragments

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
import androidx.fragment.app.Fragment
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.views.FragmentType
import com.safiej.stackoverflowapplication.views.NavigationCallback

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }

    private var navigationCallback: NavigationCallback? = null

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
            navigationCallback?.onNavigationRequest(FragmentType.RESULTS, input.text.toString())
        }
    }

    private fun setTitle() {
        val spannable = SpannableStringBuilder().append("Stack")
        val start = spannable.length
        spannable.append("Overflow")
        spannable.setSpan(StyleSpan(Typeface.BOLD), start, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleTextView.setText(spannable, TextView.BufferType.SPANNABLE)
    }

    fun setNavigationCallback(navigationCallback: NavigationCallback) {
        this.navigationCallback = navigationCallback
    }
}