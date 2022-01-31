package com.example.newsaggregator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.adapters.ArticleAdapter
import com.example.newsaggregator.adapters.CategoryAdapter
import java.util.ArrayList

/**
 * Categories fragment
 * @author Richard James
 */
class CategoriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = createCategories()
        val categoryRecycler = view.findViewById<RecyclerView>(R.id.CategoryRecycler)
        categoryRecycler.layoutManager = LinearLayoutManager(view.context)

        val adapter = CategoryAdapter(categories)
        categoryRecycler.adapter = adapter
    }

    private fun createCategories() : ArrayList<String>{
        val categories = java.util.ArrayList<String>()
        categories.add(resources.getString(R.string.cat_1))
        categories.add(resources.getString(R.string.cat_2))
        categories.add(resources.getString(R.string.cat_4))
        categories.add(resources.getString(R.string.cat_5))
        categories.add(resources.getString(R.string.cat_6))
        categories.add(resources.getString(R.string.cat_7))
        return categories
    }
}