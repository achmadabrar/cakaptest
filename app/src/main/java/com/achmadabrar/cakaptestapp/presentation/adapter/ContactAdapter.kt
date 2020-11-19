package com.achmadabrar.cakaptestapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.cakaptestapp.R
import com.achmadabrar.cakaptestapp.data.models.Contact
import com.achmadabrar.cakaptestapp.presentation.viewholder.ContactViewHolder

class ContactAdapter(
    val contacts: List<Contact>,
    val listener: ContactViewHolder.Listener
): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contacts[position].let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}