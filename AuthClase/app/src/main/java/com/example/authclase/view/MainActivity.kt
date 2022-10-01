package com.example.authclase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.authclase.R
import com.example.authclase.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    val viewModel:MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.authState.observe(this){
            when(it){
                -1->{}
                0->{}
                1->{}
            }
        }
    }

    fun regBtnAction(){
        viewModel.signUp("tuma@hotmail.com","1234")
    }

}