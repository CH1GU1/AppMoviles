package com.example.s4kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s4kotlin.databinding.FragmentTaskListBinding


class TaskListFragment : Fragment(), NewTaskFragment.OnNewTaskListener{

    private var _binding:FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    //STATE
    private val tasks = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        val view = binding.root

        //Recrear el estado
        binding.taskListTV.text = ""
        for (task in tasks){
            binding.taskListTV.append("${task}\n")
        }

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
        tasks.add(task)
    }
}