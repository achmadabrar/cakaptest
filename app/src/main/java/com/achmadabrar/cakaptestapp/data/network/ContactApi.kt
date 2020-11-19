package com.achmadabrar.cakaptestapp.data.network

import com.achmadabrar.cakaptestapp.data.models.Contact
import com.achmadabrar.cakaptestapp.data.models.ContactsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ContactApi {

    companion object {
        private const val CONTACTS = "contacts"
        private const val DETAIL = "contact/detail"
    }

    @GET(CONTACTS)
    suspend fun getListContact(): ContactsResponse

    @POST(CONTACTS)
    suspend fun insertContact(@Body contact: Contact): ContactsResponse

    @POST(DETAIL)
    suspend fun getDetail(@Body email: String): ContactsResponse
}