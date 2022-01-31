package com.example.newsaggregator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

/**
 * A class for displaying the category selection menu
 * @author Richard James
 * @param catList category list
 */
class CategoryAdapter(private val catList : ArrayList<String>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.catView.text = catList[position]
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var catView = itemView.findViewById<View>(R.id.catLabel) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val msg = catView.text
            val snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
            snackbar.show()
        }

    }



}