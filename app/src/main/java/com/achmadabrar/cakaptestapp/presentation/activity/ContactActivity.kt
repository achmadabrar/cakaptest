package com.achmadabrar.cakaptestapp.presentation.activity

import android.os.Bundle
import com.achmadabrar.cakaptestapp.R
import com.achmadabrar.cakaptestapp.core.base.BaseActivity
import com.achmadabrar.cakaptestapp.presentation.fragment.ContactFragment

class ContactActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_contact, ContactFragment())
        transaction.commit()
    }
}