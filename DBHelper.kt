package com.example.newsaggregator.database

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.newsaggregator.Article

/**
 * Class for handling the database.
 * @author Richard James
 */
class SqliteDatabase(context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * Create saved and history tables
     */
    override fun onCreate(db: SQLiteDatabase) {
        createHistory(db)
        createSaved(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SAVED")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    private fun createHistory(db: SQLiteDatabase) {
        val CREATE_TABLE_HISTORY = "CREATE TABLE $TABLE_HISTORY($COLUMN_TITLE TEXT PRIMARY KEY, $COLUMN_SOURCE TEXT, $COLUMN_DATE TEXT, $COLUMN_AUTHOR TEXT, $COLUMN_DESC TEXT, $COLUMN_URL TEXT, $COLUMN_IMAGE TEXT)"
        db.execSQL(CREATE_TABLE_HISTORY)
    }

    private fun createSaved(db: SQLiteDatabase) {
        val CREATE_TABLE_SAVED = "CREATE TABLE $TABLE_SAVED($COLUMN_TITLE TEXT PRIMARY KEY, $COLUMN_SOURCE TEXT, $COLUMN_DATE TEXT, $COLUMN_AUTHOR TEXT, $COLUMN_DESC TEXT, $COLUMN_URL TEXT, $COLUMN_IMAGE TEXT)"
        db.execSQL(CREATE_TABLE_SAVED)
    }

    /**
     * List contents from a given category table
     * @param category the category that the user wants
     */
    fun listContents(category: String) : MutableList<Article> {
        var table = TABLE_BUSINESS
        when(category) {
            "Business" -> table = TABLE_BUSINESS
            "Entertainment" -> table = TABLE_ENTERTAINMENT
            "Health" -> table = TABLE_HEALTH
            "Science" -> table = TABLE_SCIENCE
            "Sport" -> table = TABLE_SPORT
            "Technology" -> table = TABLE_TECH
            "topStories" -> table = TABLE_TOP_STORIES
        }
        val sql = "SELECT * FROM $table"
        val db = this.readableDatabase
        val storeInfo = arrayListOf<Article>()
        val cursor = db.rawQuery(sql, null)
        if(cursor.moveToFirst()) {
            do {
                val article = Article()
                article.setTitle(cursor.getString(0))
                article.setSource(cursor.getString(1))
                article.setDate(cursor.getString(2))
                article.setAuthor(cursor.getString(3))
                article.setDesc(cursor.getString(4))
                article.setURL(cursor.getString(5))
                article.setImage(cursor.getString(6))
                storeInfo.add(article)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return storeInfo
    }

    /**
     * Updates the contents of a table with the new articles
     * @param category the category being updated
     * @param articles the articles being added into the category
     */
    fun updateContents(category: String, articles: ArrayList<Article>) {
        var table = TABLE_BUSINESS
        when(category) {
            "Business" -> table = TABLE_BUSINESS
            "Entertainment" -> table = TABLE_ENTERTAINMENT
            "Health" -> table = TABLE_HEALTH
            "Science" -> table = TABLE_SCIENCE
            "Sport" -> table = TABLE_SPORT
            "Technology" -> table = TABLE_TECH
            "topStories" -> table = TABLE_TOP_STORIES
        }
        Log.e("Table Selected: ", table)
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS $table")
        db.execSQL("CREATE TABLE $table($COLUMN_TITLE TEXT PRIMARY KEY, $COLUMN_SOURCE TEXT, $COLUMN_DATE TEXT, $COLUMN_AUTHOR TEXT, $COLUMN_DESC TEXT, $COLUMN_URL TEXT, $COLUMN_IMAGE TEXT)")
        for(a in articles) {
            addArticleToDB(a, table)
        }
    }

    /**
     * Generic method for adding an article to a given table.
     * @param article the article being added
     * @param table the table the article is added too
     */
    fun addArticleToDB(article: Article, table: String) {
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, article.getTitle())
        cv.put(COLUMN_SOURCE, article.getSource())
        cv.put(COLUMN_DATE, article.getDate())
        cv.put(COLUMN_AUTHOR, article.getAuthor())
        cv.put(COLUMN_DESC, article.getDesc())
        cv.put(COLUMN_URL, article.getURL())
        cv.put(COLUMN_IMAGE, article.getImage())
        val db = this.writableDatabase
        db.insert(table, null, cv)
        Log.d("Article added to DB", article.getTitle())
    }

    /**
     * Gets all entries in the history table and returns them
     * @return Article history list of type MutableList<Article>
     */
    fun listHistory() : MutableList<Article> {
        val sql = "SELECT * FROM $TABLE_HISTORY"
        val db = this.readableDatabase
        val storeHistory = arrayListOf<Article>()
        val cursor = db.rawQuery(sql, null)
        if(cursor.moveToFirst()) {
            do {
                val article = Article()
                article.setTitle(cursor.getString(0))
                article.setSource(cursor.getString(1))
                article.setDate(cursor.getString(2))
                article.setAuthor(cursor.getString(3))
                article.setDesc(cursor.getString(4))
                article.setURL(cursor.getString(5))
                article.setImage(cursor.getString(6))
                storeHistory.add(article)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return storeHistory
    }

    /**
     * Method for adding an article to the history table
     * @param article article being added
     */
    fun addHistory(article: Article) {
        val contents = listHistory()
        if(contents.size == 5) {
            contents.removeAt(0)
        }
        contents.add(article)
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        createHistory(db)
        for(a in contents) {
            addHistoryArticleToDb(a)
        }
    }

    /**
     * Helper method for adding article to the history table
     * @param article article being added
     */
    private fun addHistoryArticleToDb(article: Article) {
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, article.getTitle())
        cv.put(COLUMN_SOURCE, article.getSource())
        cv.put(COLUMN_DATE, article.getDate())
        cv.put(COLUMN_AUTHOR, article.getAuthor())
        cv.put(COLUMN_DESC, article.getDesc())
        cv.put(COLUMN_URL, article.getURL())
        cv.put(COLUMN_IMAGE, article.getImage())
        val db = this.writableDatabase
        db.insert(TABLE_HISTORY, null, cv)
    }

    /**
     * Gets all saved articles
     * @return List of saved articles of type MutableList<Article>
     */
    fun listSaved() : MutableList<Article> {
        val sql = "SELECT * FROM $TABLE_SAVED"
        val db = this.readableDatabase
        val storeSaved = arrayListOf<Article>()
        val cursor = db.rawQuery(sql, null)
        if(cursor.moveToFirst()) {
            do {
                val article = Article()
                article.setTitle(cursor.getString(0))
                article.setSource(cursor.getString(1))
                article.setDate(cursor.getString(2))
                article.setAuthor(cursor.getString(3))
                article.setDesc(cursor.getString(4))
                article.setURL(cursor.getString(5))
                article.setImage(cursor.getString(6))
                storeSaved.add(article)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return storeSaved
    }

    /**
     * Add article to saved table
     * @param article article being saved
     */
    fun addSaved(article: Article) {
        val contents = listHistory()
        if(contents.size == 5) {
            contents.removeAt(0)
        }
        contents.add(article)
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SAVED")
        createSaved(db)
        for(a in contents) {
            addSavedArticleToDb(a)
        }
    }

    /**
     * Helper method for adding article to the saved table
     * @param article article being added
     */
    private fun addSavedArticleToDb(article: Article) {
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, article.getTitle())
        cv.put(COLUMN_SOURCE, article.getSource())
        cv.put(COLUMN_DATE, article.getDate())
        cv.put(COLUMN_AUTHOR, article.getAuthor())
        cv.put(COLUMN_DESC, article.getDesc())
        cv.put(COLUMN_URL, article.getURL())
        cv.put(COLUMN_IMAGE, article.getImage())
        val db = this.writableDatabase
        db.insert(TABLE_SAVED, null, cv)
    }

    /**
     * Method for acquring the custom news based on gathered news, saves api requests
     * @param c context
     * @return list of articles based on preferences
     */
    fun getMyNews(c: Context) : MutableList<Article> {
        val prefs = c.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val getBusi = prefs.getBoolean("busi", false)
        val getEnt = prefs.getBoolean("ent", false)
        val getHel = prefs.getBoolean("hel", false)
        val getSci = prefs.getBoolean("sci", false)
        val getSpr = prefs.getBoolean("spr", false)
        val getTech = prefs.getBoolean("tech", false)
        val data = mutableListOf<Article>()
        if(getBusi) {
            data.addAll(getArticles(TABLE_BUSINESS))
        }
        if(getEnt) {
            data.addAll(getArticles(TABLE_ENTERTAINMENT))
        }
        if(getHel) {
            data.addAll(getArticles(TABLE_HEALTH))
        }
        if(getSci) {
            data.addAll(getArticles(TABLE_SCIENCE))
        }
        if(getSpr) {
            data.addAll(getArticles(TABLE_SPORT))
        }
        if(getTech) {
            data.addAll(getArticles(TABLE_TECH))
        }
        return data
    }

    /**
     * Get articles from a table. Helper method for getMyNews()
     * @param table the table being used
     * @return wanted entries
     */
    fun getArticles(table: String) : MutableList<Article> {
        val sql = "SELECT * FROM $table LIMIT 2"
        val db = this.readableDatabase
        val storeInfo = arrayListOf<Article>()
        val cursor = db.rawQuery(sql, null)
        if(cursor.moveToFirst()) {
            do {
                val article = Article()
                article.setTitle(cursor.getString(0))
                article.setSource(cursor.getString(1))
                article.setDate(cursor.getString(2))
                article.setAuthor(cursor.getString(3))
                article.setDesc(cursor.getString(4))
                article.setURL(cursor.getString(5))
                article.setImage(cursor.getString(6))
                storeInfo.add(article)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return storeInfo
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Articles"
        private const val TABLE_HISTORY = "History"
        private const val TABLE_SAVED = "Saved"
        private const val TABLE_BUSINESS = "Business"
        private const val TABLE_ENTERTAINMENT = "Entertainment"
        private const val TABLE_HEALTH = "Health"
        private const val TABLE_SCIENCE = "Science"
        private const val TABLE_SPORT = "Sport"
        private const val TABLE_TECH = "Technology"
        private const val TABLE_TOP_STORIES = "topStories"

        private const val COLUMN_TITLE = "title"
        private const val COLUMN_SOURCE = "source"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_AUTHOR = "author"
        private const val COLUMN_DESC = "description"
        private const val COLUMN_URL = "url"
        private const val COLUMN_IMAGE = "image"
    }
}