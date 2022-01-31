package com.example.newsaggregator

import java.net.URL

/**
 * Article model class
 * @author Richard James
 */
class Article {
    private var title : String? = "No Title Provided"
    private var source : String? = "No Source Provided"
    private var image : String? = null
    private var desc : String? = "No Description Provided"
    private var author : String? = "No Author Provided"
    private var url : String? = null
    private var datePublished : String? = null

    fun getTitle() : String? {
        return title
    }

    fun setTitle(title : String?) {
        this.title = title
    }

    fun getSource() : String? {
        return source
    }

    fun setSource(source : String?) {
        this.source = source
    }

    fun getImage() : String? {
        return this.image
    }

    fun setImage(image : String?) {
        this.image = image
    }

    fun getDesc() : String? {
        return this.desc
    }

    fun setDesc(desc : String?) {
        this.desc = desc
    }

    fun getAuthor() : String? {
        return this.author
    }

    fun setAuthor(author : String?) {
        this.author = author
    }

    fun getURL() : String? {
        return this.url
    }

    fun setURL(url : String?) {
        this.url = url
    }

    fun getDate() : String? {
        return this.datePublished
    }

    fun setDate(date : String?) {
        this.datePublished = date
    }
}