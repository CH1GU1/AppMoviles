package com.example.firestoreejemplokotlinkevin.models

import java.io.Serializable

data class Message(
    val id: String = "",
    val message: String = "",
    val userID: String = "",
    val date: String = ""
)