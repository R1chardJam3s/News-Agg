package com.example.newsaggregator.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsaggregator.CategoriesFragment
import com.example.newsaggregator.MyNewsFragment
import com.example.newsaggregator.TopStoriesFragment

/**
 * Adapter for Main Activity tab layout
 * @author Richard James
 */
class TabAdapter (activity : AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return TopStoriesFragment()
            1 -> return MyNewsFragment()
            2 -> return CategoriesFragment()
        }
        return TopStoriesFragment()
    }

    override fun getItemCount(): Int {
        return 3
    }
}