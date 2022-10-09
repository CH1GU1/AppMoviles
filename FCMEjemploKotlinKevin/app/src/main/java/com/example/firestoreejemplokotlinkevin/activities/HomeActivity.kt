package com.example.firestoreejemplokotlinkevin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.firestoreejemplokotlinkevin.databinding.ActivityHomeBinding
import com.example.firestoreejemplokotlinkevin.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private lateinit var adapter: ArrayAdapter<User>
    private lateinit var users: ArrayList<User>

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //CARGAR EL USUARIO DE LOS PREFERENCES
        val user = loadUser()
        if(user == null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        } else {
            this.user = user
        }

        Firebase.messaging.subscribeToTopic(user.id)

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

        binding.logoutBtn.setOnClickListener {
            finish()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            Firebase.messaging.unsubscribeFromTopic(user.id)
            val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
            sp.edit().clear().apply()
        }

    }
    fun loadUser() :User?{
        val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
        val json = sp.getString("user","NO_USER")
        if(json == "NO_USER"){
            return null
        }else{
            return Gson().fromJson(json, User::class.java)
        }
    }
}