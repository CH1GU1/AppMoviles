package com.example.firestoreejemplokotlinkevin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firestoreejemplokotlinkevin.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameET.text.toString()
            val password = binding.passET.text.toString()
            val user = User(UUID.randomUUID().toString(), username, password)

            val query = Firebase.firestore.collection("users").whereEqualTo("username",username)
            query.get().addOnCompleteListener{ task ->
                //Si el usuario no existe, crearlo e iniciar sesiom
                if(task.result?.size()==0){
                    Firebase.firestore.collection("users").document(user.id).set(user)
                    val intent = Intent(this,HomeActivity::class.java).apply {
                        putExtra("user",user)
                    }
                    startActivity(intent)
                }

                //Si ya existe, descargar el usuario e iniciar sesion con el
                else{
                    lateinit var existuser : User
                    for (document in task.result!!){
                        existuser = document.toObject(User::class.java)
                        break
                    }
                    if (existuser.pass == password){
                        val intent = Intent(this,HomeActivity::class.java).apply {
                            putExtra("user",existuser)
                        }
                        startActivity(intent)
                    }else {
                        Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }
}