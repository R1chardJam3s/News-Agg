package com.example.newsaggregator

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.newsaggregator.database.SqliteDatabase
import java.util.*

/**
 * Service class used to update API
 * @author Richard James
 */
class APIService() : Service() {

    private val interval = 3600L * 1000L
    private var mTimer : Timer? = null
    private val mDatabase = SqliteDatabase(this)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(mTimer != null) {
            mTimer!!.cancel()
        } else {
            mTimer = Timer()
        }
        mTimer!!.scheduleAtFixedRate(Task(),0,interval)
        return START_STICKY
    }

    inner class Task : TimerTask() {

        override fun run() {
            //Update databases
            NewsFetcher.getTopStories { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("topStories", articles)
            }
            NewsFetcher.getBusinessNews { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("Business", articles)
            }
            NewsFetcher.getEntNews { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("Entertainment", articles)
            }
            NewsFetcher.getHealthNews { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("Health", articles)
            }
            NewsFetcher.getScienceNews { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("Science", articles)
            }
            NewsFetcher.getSportsNews { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("Sport", articles)
            }
            NewsFetcher.getTechNews { articles ->
                Thread.sleep(2000)
                mDatabase.updateContents("Technology", articles)
            }
        }

    }
}