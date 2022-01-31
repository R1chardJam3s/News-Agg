package com.example.newsaggregator.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import androidx.core.app.NotificationCompat
import com.example.newsaggregator.Article
import com.example.newsaggregator.R

/**
 * Class for helping make notifications.
 * @author Richard James
 */
internal class NotificationHelper(base: Context) : ContextWrapper(base) {

    private var notifManager : NotificationManager? = null

    private val manager: NotificationManager? get() {
        if(notifManager == null) {
            notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return notifManager
    }

    init {
        createChannel()
    }

    fun createChannel() {
        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.setShowBadge(true)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        manager!!.createNotificationChannel(notificationChannel)
    }

    fun getNotification(article : Article) : NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(article.getTitle())
            .setContentText(article.getSource())
            .setSmallIcon(R.drawable.actual_news_logo)
            .setAutoCancel(true)
    }

    fun notify(id: Int, notification: NotificationCompat.Builder) {
        manager!!.notify(id, notification.build())
    }

    companion object {
        const val CHANNEL_ID = "com.example.newsaggregator.TWO"
        const val CHANNEL_NAME = "Channel Two"
    }
}