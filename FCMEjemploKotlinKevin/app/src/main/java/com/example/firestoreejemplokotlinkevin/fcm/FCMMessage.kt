package com.example.firestoreejemplokotlinkevin.fcm

data class FCMMessage<T:Any> (
    var to: String ="",
    var data:  T? = null
        )