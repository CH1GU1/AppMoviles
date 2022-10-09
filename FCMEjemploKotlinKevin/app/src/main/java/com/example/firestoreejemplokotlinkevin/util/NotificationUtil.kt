package com.example.firestoreejemplokotlinkevin.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.firestoreejemplokotlinkevin.R
import com.example.firestoreejemplokotlinkevin.activities.HomeActivity

object NotificationUtil {

    private val CHANNEL_ID = "messsages"
    private val CHANNEL_NAME = "Messages"
    private var id = 0



    fun showNotification(context: Context, title:String, message:String){

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, HomeActivity::class.java)
        val pending = PendingIntent.getActivity(context, 1,intent,PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID).setDefaults(Notification.DEFAULT_ALL)
            .setContentText(message)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.icononotification)
            .setContentIntent(pending)

        val notification = builder.build()
        notificationManager.notify(id, notification)
        id++
    }

}