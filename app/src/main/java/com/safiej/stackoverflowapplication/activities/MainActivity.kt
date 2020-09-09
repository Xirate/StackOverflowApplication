package com.safiej.stackoverflowapplication.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.safiej.stackoverflowapplication.views.adapters.MainPagerAdapter
import com.safiej.stackoverflowapplication.R
import com.safiej.stackoverflowapplication.views.Navigator
import com.safiej.stackoverflowapplication.views.NavigatorInterface
import com.safiej.stackoverflowapplication.views.ResultsFragment
import com.safiej.stackoverflowapplication.views.SearchFragment
import com.safiej.stackoverflowapplication.views.adapters.FragmentType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity(), NavigatorInterface {

    private val SEARCH_FRAGMENT_TAG = "SearchFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigator.navigatorInterface = this
        showFirstFragmentIfShould()
    }

    override fun onNavigationRequest(fragmentType: FragmentType, argument: String) {
        val fragmentManager = supportFragmentManager
        if(fragmentType == FragmentType.RESULTS) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ResultsFragment.newInstance(argument))
                    .commit()
        } else if (fragmentType == FragmentType.DETAILS) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(argument))
            startActivity(browserIntent)
        }
    }

    private fun showFirstFragmentIfShould() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, SearchFragment.newInstance())
                    .commit()
        }
    }
}
