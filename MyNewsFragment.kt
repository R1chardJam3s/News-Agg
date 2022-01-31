package com.example.newsaggregator

import android.os.Bundle
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
 * Fragment for My News
 * @author Richard James
 */
class MyNewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mDatabase = SqliteDatabase(this.context!!)
        val articles = mDatabase.getMyNews(this.context!!) as ArrayList<Article>

        val recyclerView = view.findViewById<RecyclerView>(R.id.articleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val adapter = ArticleAdapter(articles)
        recyclerView.adapter = adapter
    }
}