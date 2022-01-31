package com.example.newsaggregator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.adapters.ArticleAdapter
import java.util.ArrayList

/**
 * Fragment for searching keywords
 * @author Richard James
 */
class SearchFragment(query: String) : Fragment() {

    val q = query

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        NewsFetcher.getSearch(q) { articles ->
            val recyclerView = view.findViewById<RecyclerView>(R.id.articleRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(view.context)

            val adapter = ArticleAdapter(articles)
            recyclerView.adapter = adapter
        }


        Thread.sleep(2000)
        Log.d("View created", "inflated")
        return view
    }
}