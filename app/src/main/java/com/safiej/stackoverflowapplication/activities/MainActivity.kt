package com.safiej.stackoverflowapplication.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.safiej.stackoverflowapplication.MainPagerAdapter
import com.safiej.stackoverflowapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.activity_main_view_pager)

        val pagerAdapter = MainPagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }
}
