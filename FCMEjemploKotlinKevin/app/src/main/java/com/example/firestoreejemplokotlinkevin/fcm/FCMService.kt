package com.example.firestoreejemplokotlinkevin.fcm

import com.example.firestoreejemplokotlinkevin.models.Message
import com.example.firestoreejemplokotlinkevin.util.NotificationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.json.JSONObject

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val obj = JSONObject(message.data as Map<*, *>)
        val json = obj.toString()
        val message = Gson().fromJson(json, Message::class.java)
        NotificationUtil.showNotification(this, "Mensaje Nuevo", message.message)
    }
}