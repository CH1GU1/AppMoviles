package com.example.s4kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.s4kotlin.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var newTaskFragment: NewTaskFragment
    private lateinit var taskListFragment: TaskListFragment
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        newTaskFragment = NewTaskFragment.newInstance()
        taskListFragment = TaskListFragment.newInstance()

        //Suscripcion
        newTaskFragment.listener = taskListFragment


        showFragment(newTaskFragment) //Ejecutado al abrir la app

        binding.navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.newItem){
                showFragment(newTaskFragment)
            } else if(menuItem.itemId == R.id.listItem){
                showFragment(taskListFragment)
            }
            true
        }
    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

}