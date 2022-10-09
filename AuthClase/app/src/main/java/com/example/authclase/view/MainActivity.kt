package com.example.authclase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.authclase.R
import com.example.authclase.databinding.ActivityMainBinding
import com.example.authclase.viewmodel.AuthResult
import com.example.authclase.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    val viewModel:MainViewModel by viewModels()

    val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.authState.observe(this){
            when(it.result){
                AuthResult.IDLE->{

                }
                AuthResult.SUCCESS->{
                    Toast.makeText(this,"Registrado con exito",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                }
                AuthResult.FAIL->{
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.regBtn.setOnClickListener{
            regBtnAction()
        }
    }

    fun regBtnAction(){
        var name = binding.nameET.text.toString()
        val phone = binding.phoneET.text.toString()
        val email = binding.emailET.text.toString()
        val pass = binding.passET.text.toString()

        viewModel.signUp(name,phone,email,pass)
    }

}