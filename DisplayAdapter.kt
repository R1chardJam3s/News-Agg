package com.example.newsaggregator.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsaggregator.*

/**
 * Adapter for the Display class
 * @author Richard James
 */
class DisplayAdapter(activity : AppCompatActivity, type : String) : FragmentStateAdapter(activity) {

    private val cat = type

    override fun getItemCount(): Int {
        return 1
    }

    /**
     * Method for creating fragment based on the type needed
     */
    override fun createFragment(position: Int): Fragment {

        when(cat) {
            "Business" -> return BusinessFragment()
            "Entertainment" -> return EntFragment()
            "Health" -> return HealthFragment()
            "Science" -> return SciFragment()
            "Sport" -> return SportFragment()
            "Technology" -> return TechFragment()
            "Select Categories" -> return CatPrefFragment()
            "View History" -> return HistoryFragment()
            "Save for Later" -> return SavedFragment()
            else -> {
                return SearchFragment(cat)
            }
        }
    }

}