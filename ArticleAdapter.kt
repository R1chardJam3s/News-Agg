package com.example.newsaggregator.adapters

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.Article
import com.example.newsaggregator.R
import com.example.newsaggregator.database.SqliteDatabase
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.articles.view.*
import java.util.ArrayList

/**
 * Adapter class for Articles. Controls functionality.
 * @author Richard James
 * @param articleList the list of articles that need to have functionality applied.
 */
class ArticleAdapter(private val articleList : ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.articles, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.article = article
        holder.titleView.text = article.getTitle()
        holder.descView.text = article.getDesc()
        holder.authView.text = article.getAuthor()
        holder.sourceView.text = article.getSource()
        Picasso.get().load(article.getImage()).into(holder.imgView)
        holder.url = article.getURL()
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{

        var titleView = itemView.findViewById<View>(R.id.titleLabel) as TextView
        var descView = itemView.findViewById<View>(R.id.descLabel) as TextView
        var authView = itemView.findViewById<View>(R.id.authorLabel) as TextView
        var sourceView = itemView.findViewById<View>(R.id.sourceLabel) as TextView
        var imgView = itemView.findViewById<View>(R.id.articleImage) as ImageView
        var url : String? = null
        var article : Article? = null

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        /**
         * On click, add the article to history and open the URL in browser
         * @param v the view that the function is called from.
         */
        override fun onClick(v: View) {
            val mDatabase = SqliteDatabase(v.context)
            mDatabase.addHistory(article!!)
            val website = Uri.parse(url)
            val viewIntent = Intent(Intent.ACTION_VIEW, website)
            v.context.startActivity(viewIntent)
        }

        /**
         * On hold, open pop up menu and allow the user to share or save for later.
         * @param v the view that the function is called from.
         */
        override fun onLongClick(v: View): Boolean {
            val popUp = PopupMenu(v.context,v)
            popUp.inflate(R.menu.hold_menu)
            popUp.setOnMenuItemClickListener{item ->
                when(item.itemId) {
                    R.id.Share -> sendURL(v)
                    R.id.Save -> saveArticle(v)
                    else -> true
                }
            }
            popUp.show()
            return true
        }

        /**
         * Method for sending the URL to someone or another application.
         * @param view the view that the function is called from.
         */
        private fun sendURL(view: View) : Boolean {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/html"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            view.context.startActivity(shareIntent)
            return true
        }

        /**
         * Method for saving an article and displayed a snackbar.
         * @param view the view that the function is called from.
         */
        private fun saveArticle(view: View) : Boolean {
            val mDatabase = SqliteDatabase(view.context)
            mDatabase.addSaved(article!!)
            val msg = "Article Saved"
            val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            snackbar.show()
            return true
        }
    }

}