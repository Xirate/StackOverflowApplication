package com.safiej.stackoverflowapplication.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.views.*
import com.safiej.stackoverflowapplication.views.fragments.ResultsFragment
import com.safiej.stackoverflowapplication.views.fragments.SearchFragment

class MainActivity : FragmentActivity(), NavigationCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFirstFragmentIfShould()
    }

    override fun onNavigationRequest(fragmentType: FragmentType, argument: String) {
        val fragmentManager = supportFragmentManager
        if(fragmentType == FragmentType.RESULTS) {
            val fragment = ResultsFragment.newInstance(argument).apply {
                setNavigationCallback(this@MainActivity)
            }
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
        } else if (fragmentType == FragmentType.DETAILS) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(argument))
            startActivity(browserIntent)
        }
    }

    private fun showFirstFragmentIfShould() {
        val fragmentManager = supportFragmentManager
        val fragment = SearchFragment.newInstance().apply {
            setNavigationCallback(this@MainActivity)
        }
        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}
