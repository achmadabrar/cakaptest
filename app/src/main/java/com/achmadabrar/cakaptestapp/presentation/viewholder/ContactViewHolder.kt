package com.achmadabrar.cakaptestapp.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.cakaptestapp.R
import com.achmadabrar.cakaptestapp.data.models.Contact
import kotlinx.android.synthetic.main.item_layout_contact.view.*

class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(contact: Contact, listener: Listener?) {
        with(itemView) {
            tv_name.text = resources.getString(R.string.name_with_data, contact.name)
            tv_email.text = resources.getString(R.string.email_with_data, contact.email)
            tv_phone.text = resources.getString(R.string.phone_with_data, contact.phoneNumber.toString())
            tv_created_at.text = resources.getString(R.string.crated_at_with_data, contact.createdAt.toString())
        }
    }

    interface Listener {
        fun onClickContatc(contact: Contact)
    }
}