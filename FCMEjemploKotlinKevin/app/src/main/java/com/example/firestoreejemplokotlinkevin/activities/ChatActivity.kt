package com.example.firestoreejemplokotlinkevin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.lifecycleScope
import com.example.firestoreejemplokotlinkevin.databinding.ActivityChatBinding
import com.example.firestoreejemplokotlinkevin.fcm.FCMMessage
import com.example.firestoreejemplokotlinkevin.models.Chat
import com.example.firestoreejemplokotlinkevin.models.Message
import com.example.firestoreejemplokotlinkevin.models.User
import com.example.firestoreejemplokotlinkevin.util.HTTPSWebUtilDomi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.DocumentChange
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var contact: User

    private lateinit var binding : ActivityChatBinding

    private lateinit var chat: Chat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.messagesTV.movementMethod = ScrollingMovementMethod()

        user = intent.extras?.get("user") as User
        contact = intent.extras?.get("contact") as User

        Firebase.firestore.collection("users").document(user.id).collection("chats")
            .whereEqualTo("contactID",contact.id).get().addOnCompleteListener{ task ->
                if(task.result?.size() == 0){
                    createChat()
                } else {
                    for (document in task.result!!){
                       chat = document.toObject(Chat::class.java)
                       break
                    }
                }
                //Leer mensajes entre ambos
                getMessages()
            }

        binding.sendBtn.setOnClickListener {
            val message = Message(UUID.randomUUID().toString(), binding.messageET.text.toString(), user.id, Date().time.toString())
            Firebase.firestore.collection("chats").document(chat.id).collection("messages").document(message.id).set(message)

            //Notificar al otro usuario el mensaje
            lifecycleScope.launch(Dispatchers.IO){
                val obj = FCMMessage("/topics/${contact.id}",message)
                val json = Gson().toJson(obj)
                HTTPSWebUtilDomi().POSTtoFCM(json)

            }
        }

    }

    private fun getMessages() {
        Firebase.firestore.collection("chats").document(chat.id).collection("messages").orderBy("date").limitToLast(10).addSnapshotListener { value, error ->

        for(change in value!!.documentChanges){
            when(change.type){
                com.google.firebase.firestore.DocumentChange.Type.ADDED->{
                    val message = change.document.toObject(Message::class.java)
                    binding.messagesTV.append("${message.message}\n\n")
                }
            }
        }
        }
    }

    private fun createChat() {
        val id = UUID.randomUUID().toString()
        chat = Chat(id, contact.id)
        val foreingChat = Chat(id, user.id)
        Firebase.firestore.collection("users").document(user.id).collection("chats").document(id).set(chat)
        Firebase.firestore.collection("users").document(contact.id).collection("chats").document(id).set(foreingChat)

    }
}