package com.achmadabrar.cakaptestapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    val createdAt: Long,
    val email: String,
    @PrimaryKey val name: String,
    val phoneNumber: Long
)