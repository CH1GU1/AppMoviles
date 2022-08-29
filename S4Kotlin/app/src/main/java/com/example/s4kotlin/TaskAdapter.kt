package com.example.s4kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>(){

    private val tasks = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.taskrow, parent, false)
        val taskViewHolder = TaskViewHolder(view)
        return taskViewHolder

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskn = tasks[position]
        holder.taskTextRow.text = taskn.task
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun addTask(task: Task){
        tasks.add(task)
        //Notificar (no se notifica en este caso porq no se esta viendo en ese momento)
    }



}