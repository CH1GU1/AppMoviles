package com.example.s4kotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.s4kotlin.databinding.FragmentNewTaskBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment : Fragment() {

    private var _binding:FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    //Listener
    var listener : OnNewTaskListener? = null


    //_Binding -> NULLEABLE
    //Binding -> NON-NULLEABLE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewTaskBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.addBtn.setOnClickListener {
            val text = binding.newItemET.text.toString()
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()

            //Publicacion
            listener?.let {
                it.onNewTask(text)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnNewTaskListener{
        fun onNewTask(task:String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewTaskFragment()
    }
}