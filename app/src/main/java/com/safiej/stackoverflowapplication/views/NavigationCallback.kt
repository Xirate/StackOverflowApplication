package com.safiej.stackoverflowapplication.views


interface NavigationCallback {
    fun onNavigationRequest(fragmentType: FragmentType, argument: String)
}

enum class FragmentType {
    SEARCH,
    RESULTS,
    DETAILS
}