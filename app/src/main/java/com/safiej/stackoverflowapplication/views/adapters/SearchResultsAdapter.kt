package com.safiej.stackoverflowapplication.views.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.model.Question
import com.safiej.stackoverflowapplication.views.Navigator

class SearchResultsAdapter : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    private var questionList = ArrayList<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return SearchResultsViewHolder(view)
    }

    override fun getItemCount(): Int = questionList.size

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bindViewHolder(questionList[position])
    }

    fun setData(data: ArrayList<Question>) {
        questionList = data
        notifyDataSetChanged()
    }

    class SearchResultsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var questionTitle: TextView = view.findViewById(R.id.item_question_title)
        private var questionContent: TextView = view.findViewById(R.id.item_question_content)
        private var authorAvatar: ImageView = view.findViewById(R.id.item_question_author_avatar)
        private var authorName: TextView = view.findViewById(R.id.item_question_author_name)

        fun bindViewHolder(question: Question) {
            questionTitle.text = question.title
            questionContent.text = HtmlCompat.fromHtml(question.body, HtmlCompat.FROM_HTML_MODE_COMPACT)
            Glide
                .with(itemView)
                .load(question.owner.avatarUrl)
                .into(authorAvatar)
            authorName.text = question.owner.name
            itemView.setOnClickListener {
                Navigator.requestNavigation(FragmentType.DETAILS, question.url)
            }
        }
    }
}
