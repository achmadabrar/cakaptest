package com.achmadabrar.cakaptestapp.data.models

data class ContactsResponse(
    val data: Data,
    val messages: List<String>,
    val status: String
)