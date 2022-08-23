package com.example.semana3kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter : RecyclerView.Adapter<ContactView>(), ContactView.OnContactDelete
{

    private val contacts = ArrayList<Contact>()

    fun addContact(contact: Contact){
        contacts.add(contact)
        notifyItemInserted(contacts.size-1)
    }

    init {
        contacts.add(Contact("A", "Andrés","3102451854"))
        contacts.add(Contact("B", "Julian","3201548957"))
        contacts.add(Contact("C", "Alejandro","1520348512"))
        contacts.add(Contact("D", "Camilo","2541023587"))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactView {

        //Inflater: XML lo convierte a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.contactrow, parent, false) //Este row ya es de tipo row
        val contactView = ContactView(row)
        contactView.listener = this
        return  contactView

    }

    override fun onBindViewHolder(skeleton: ContactView, position: Int) {
        val contact = contacts[position]
        skeleton.contact = contact
        skeleton.nameRow.text = contact.name
        skeleton.telRow.text = contact.tel

    }

    override fun getItemCount(): Int {
        return contacts.size

    }

    override fun onDelete(contact: Contact) {
        val index = contacts.indexOf(contact)
        contacts.removeAt(index)
        notifyItemRemoved(index)
    }


}