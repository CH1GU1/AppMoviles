package com.example.s4kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s4kotlin.databinding.FragmentTaskListBinding
import java.util.*


class TaskListFragment : Fragment(), NewTaskFragment.OnNewTaskListener{

    private var _binding:FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    //STATE
    //private val tasks = ArrayList<String>()
    private val adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        val view = binding.root

        /*
        //Recrear el estado
        binding.taskListTV.text = ""
        for (task in tasks){
            binding.taskListTV.append("${task}\n")
        }
        */
        val taskRecycler = binding.taskRecycler
        taskRecycler.setHasFixedSize(true)
        taskRecycler.layoutManager = LinearLayoutManager(activity)
        taskRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TaskListFragment()
    }

    //Metodo que se ejecuta desde el NewTaskFragment
    override fun onNewTask(task: String) {
        //Modificamos el estado
        val newTask = Task(UUID.randomUUID().toString(), task)
        adapter.addTask(newTask)
    }
}