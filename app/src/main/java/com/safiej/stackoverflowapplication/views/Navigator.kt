package com.safiej.stackoverflowapplication.views

import com.safiej.stackoverflowapplication.views.adapters.FragmentType

object Navigator {
    var navigatorInterface: NavigatorInterface? = null

    fun requestNavigation(fragmentType: FragmentType, argument: String) {
        navigatorInterface?.onNavigationRequest(fragmentType, argument)
    }
}

interface NavigatorInterface {
    fun onNavigationRequest(fragmentType: FragmentType, argument: String)
}