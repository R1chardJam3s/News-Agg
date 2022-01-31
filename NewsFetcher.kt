package com.example.newsaggregator

import android.util.Log
import com.dfl.newsapi.NewsApiRepository
import com.dfl.newsapi.enums.Category
import com.dfl.newsapi.enums.Country
import com.dfl.newsapi.model.ArticleDto
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * Object used to fetch all the news using the api
 * @author Richard James
 */
object NewsFetcher {

    /**
     * Method for getting top stories
     */
    fun getTopStories(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.GENERAL, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting business news
     */
    fun getBusinessNews(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.BUSINESS, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title : String? = article.title
                val source : String? = article.source.name
                val date : String? = article.publishedAt
                val author : String? = article.author
                val desc : String? = article.description
                val url : String? = article.url
                val image : String? = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting entertainment news
     */
    fun getEntNews(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.ENTERTAINMENT, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting health news
     */
    fun getHealthNews(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.HEALTH, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting science news
     */
    fun getScienceNews(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.SCIENCE, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting sports news
     */
    fun getSportsNews(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.SPORTS, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting technology news
     */
    fun getTechNews(callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.TECHNOLOGY, country = Country.GB, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for getting news based off a search
     * @param query the keyword(s) in the search
     */
    fun getSearch(query: String, callback: (ArrayList<Article>) -> Unit) {
        val articleList = ArrayList<Article>()
        val newsApiRepository = NewsApiRepository("2e2eafadb1f64115b46493321d45ae38")
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(newsApiRepository.getTopHeadlines(sources = "bbc-news", q = query, pageSize = 5, page = 1)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMapIterable { articles -> articles.articles }
            .subscribe({ article ->
                val title = article.title
                val source = article.source.name
                val date = article.publishedAt
                val author = article.author
                val desc = article.description
                val url = article.url
                val image = article.urlToImage
                articleList.add(processArticles(title, source, date, author, desc, url, image))
                Log.d("getEverything article", article.title) },
                { t -> Log.d("getEverything error", t.message) }))
        callback(articleList)
    }

    /**
     * Method for taking values and placing them into an article
     */
    private fun processArticles(title: String?, source: String?, date: String?, author: String?, desc: String?, url: String?, image: String?) : Article {
        val article = Article()
        article.setTitle(title)
        article.setSource(source)
        article.setDate(date)
        article.setAuthor(author)
        article.setDesc(desc)
        article.setURL(url)
        article.setImage(image)
        Log.d("Article has been processed", title)
        return article
    }

    /**
     * Method used to create 'dummy' articles
     */
    fun populateArticles() : ArrayList<Article> {
        val articles = ArrayList<Article>()
        for(i in 0..4) {
            val article = Article()
            article.setTitle("Article $i")
            article.setAuthor("Richard j")
            article.setSource("Telegraph")
            article.setDesc("This is an article, it is interesting.")
            articles.add(article)
        }
        return articles
    }
}