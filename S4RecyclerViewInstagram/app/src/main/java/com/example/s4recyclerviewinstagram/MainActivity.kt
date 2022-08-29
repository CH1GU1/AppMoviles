package com.example.s4recyclerviewinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        layoutManager = LinearLayoutManager(this)

        postRecycler.layoutManager = layoutManager
        postRecycler.setHasFixedSize(true)

        adapter = PostsAdapter()
        postRecycler.adapter = adapter

        addBtn.setOnClickListener {
            Toast.makeText(this, "Añadiendo", Toast.LENGTH_SHORT).show()
            val post = Post(UUID.randomUUID().toString(),"Domiciano Rincón","Abu Dabi, Dubai",UUID.randomUUID().toString(),1000,"Kev_m07","Re crack profe, máquina, ídolo, monstruo", "Hace 1 hora",UUID.randomUUID().toString())
            adapter.addPost(post)
        }

    }
}