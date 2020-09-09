package com.safiej.stackoverflowapplication.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.safiej.stackoverflowapplication.views.ResultsFragment
import com.safiej.stackoverflowapplication.views.SearchFragment

class MainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentMap: LinkedHashMap<FragmentType, String> = LinkedHashMap()

    override fun getItemCount(): Int = fragmentMap.size

    override fun createFragment(position: Int): Fragment {
        val fragmentType = fragmentMap.keys.toTypedArray()[position]
        val argument = fragmentMap[fragmentType] ?: ""
        return when (fragmentType) {
            FragmentType.SEARCH -> SearchFragment.newInstance()
            else -> ResultsFragment.newInstance(argument)
//            FragmentType.DETAILS -> DetailsFragment.newInstance()
        }
    }

    fun pushFragmentToList(fragmentType: FragmentType, argument: String = "") {
        fragmentMap[fragmentType] = argument
        notifyDataSetChanged()
    }

    fun removeFragmentFromList(fragmentType: FragmentType) {
        fragmentMap.remove(fragmentType)
        notifyDataSetChanged()
    }
}

enum class FragmentType {
    SEARCH,
    RESULTS,
    DETAILS
}