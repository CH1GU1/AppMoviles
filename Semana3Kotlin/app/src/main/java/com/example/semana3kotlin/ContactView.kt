package com.example.semana3kotlin

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactView(itemView: View) : RecyclerView.ViewHolder(itemView){

    var contact : Contact? = null

    //Listeners
    var listener : OnContactDelete? = null


    //Identificar los elementos UI components
    var nameRow: TextView = itemView.findViewById(R.id.nameRow)
    var telRow: TextView = itemView.findViewById(R.id.telRow)
    var imageRow: ImageView = itemView.findViewById(R.id.imageRow)
    var deleteBtn: Button = itemView.findViewById(R.id.deleteBtn)

    init {
        deleteBtn.setOnClickListener {
            contact?.let {
                listener?.onDelete(it)
            }
            //Toast.makeText(itemView.context, contact?.name, Toast.LENGTH_SHORT).show()
        }
    }

    interface OnContactDelete{
        fun onDelete(contact: Contact)
    }

}