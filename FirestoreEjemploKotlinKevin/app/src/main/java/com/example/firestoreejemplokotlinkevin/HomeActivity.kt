package com.example.firestoreejemplokotlinkevin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.firestoreejemplokotlinkevin.databinding.ActivityHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private lateinit var adapter: ArrayAdapter<User>
    private lateinit var users: ArrayList<User>

    private lateinit var user:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.extras?.get("user") as User

        users = ArrayList()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,users)
        binding.userListView.adapter = adapter

        Firebase.firestore.collection("users").get().addOnCompleteListener{task ->
            for(doc in task.result!!){
                val user = doc.toObject(User::class.java)
                users.add(user)
                adapter.notifyDataSetChanged()
            }
        }

        binding.userListView.setOnItemClickListener { adapterView, view, position, id ->
            val contact = users[position]
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("contact",contact)
                putExtra("user",user)
            }
            startActivity(intent)
        }

    }
}