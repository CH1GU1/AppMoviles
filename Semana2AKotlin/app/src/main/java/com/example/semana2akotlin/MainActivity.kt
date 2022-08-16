package com.example.semana2akotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private var alfa : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestPermissions(arrayOf(
          android.Manifest.permission.CALL_PHONE
        ),0)




        val launcher = registerForActivityResult(StartActivityForResult(), ::onResult)


        loginBtn.setOnClickListener {

            val username = usernameET.text.toString()
            val password = passwordET.text.toString()
            Toast.makeText(this, concatenar(username,password), Toast.LENGTH_LONG).show()

            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("username", username)
            }
            launcher.launch(intent)
        }
    }

    private fun onResult(result: ActivityResult){
        if (result.resultCode == RESULT_CANCELED){
            Toast.makeText(this, "El usuario no esta correctamente formateado", Toast.LENGTH_LONG).show()
        }else if(result.resultCode == RESULT_OK){
            val data = result.data
            val newUser = data?.extras?.getString("newUser")
            usernameET.setText(newUser)
        }
    }


    fun concatenar(a:String, b:String) : String{
        return "${a}:${b}"
    }

}