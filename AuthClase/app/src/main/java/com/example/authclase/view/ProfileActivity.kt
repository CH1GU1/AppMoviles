package com.example.authclase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.authclase.R
import com.example.authclase.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //Descargar un objeto
        if(Firebase.auth.currentUser == null){
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
            return
        }
        //Descargar un objeto
        getByInfo()
     }
    fun getByInfo(){
        lifecycleScope.launch(Dispatchers.IO){
            val result = Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).get().await()
            val user = result.toObject(User::class.java)
            withContext(Dispatchers.Main){
                Toast.makeText(this@ProfileActivity,"Todo good ${user?.name}",Toast.LENGTH_LONG).show()
            }
        }
    }
}