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
import com.example.newsaggregator.database.SqliteDatabase
import java.util.ArrayList

/**
 * Business Fragment
 * @author Richard James
 */
class BusinessFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        val mDatabase = SqliteDatabase(this.context!!)
        val articles = mDatabase.listContents("Business") as ArrayList<Article>

        val recyclerView = view.findViewById<RecyclerView>(R.id.articleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val adapter = ArticleAdapter(articles)
        recyclerView.adapter = adapter

        Log.d("View created; inflated", "Business")
        return view
    }
}