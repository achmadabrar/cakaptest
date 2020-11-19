package com.achmadabrar.cakaptestapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.cakaptestapp.core.base.BaseViewModel
import com.achmadabrar.cakaptestapp.data.database.ContactDao
import com.achmadabrar.cakaptestapp.data.models.Contact
import com.achmadabrar.cakaptestapp.data.network.ContactApi
import com.achmadabrar.cakaptestapp.data.network.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    val contactApi: ContactApi,
    val contactDao: ContactDao
): BaseViewModel<ContactViewModel>(){

    val networkLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    private val supervisorJob = SupervisorJob()
    val contactLiveData: MutableLiveData<List<Contact>> = MutableLiveData()
    var successMessage = ""

    val networkInsertLiveData: MutableLiveData<NetworkState> = MutableLiveData()

    init {
        getList()
    }

    fun getList() {
        ioScope.launch(getJobErrorHandler() + supervisorJob) {
            networkLiveData.postValue(NetworkState.LOADING)
            val contacts = contactApi.getListContact()
            if (!contacts.data.contacts.isNullOrEmpty()) {
                contactLiveData.postValue(contacts.data.contacts)
                networkLiveData.postValue(NetworkState.LOADED)
                contactDao.insertListContact(contacts.data.contacts)
            }
        }
    }

    fun insertNewContact(name: String, email: String, phone: Long, cratedAt: Long) {
        val contact = Contact(name = name, createdAt = cratedAt, email = email, phoneNumber = phone)
        ioScope.launch(getJobErrorHandler() + supervisorJob) {
            networkInsertLiveData.postValue(NetworkState.LOADING)
            val response = contactApi.insertContact(contact)
            if (!response.messages.isNullOrEmpty()) {
                successMessage = response.messages[0]
                networkInsertLiveData.postValue(NetworkState.LOADED)
                successMessage = response.messages[0]
                contactDao.insertListContact(response.data.contacts)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e("errorFailed", "An error happened: $e")
        networkLiveData.postValue(NetworkState.FAILED)
        networkInsertLiveData.postValue(NetworkState.fialed(e.localizedMessage))
        networkInsertLiveData.postValue(NetworkState.FAILED)
    }

    fun contactByNameDsc(): List<Contact>? {
       val contacts =  contactDao.getContactByNameDsc()
        contactLiveData.postValue(contacts)
        return contacts
    }

    fun contactByNameAsc() {
        val contacts =  contactDao.getContactByNameAsc()
        contactLiveData.postValue(contacts)
    }

    fun contactByEmailDsc() {
        val contacts =  contactDao.getContactByEmailDsc()
        contactLiveData.postValue(contacts)
    }

    fun contactByEmailAsc() {
        val contacts =  contactDao.getContactByEmailAsc()
        contactLiveData.postValue(contacts)
    }

    fun contactByPhoneDsc() {
        val contacts =  contactDao.getContactByPhoneDsc()
        contactLiveData.postValue(contacts)
    }

    fun contactByPhoneAsc() {
        val contacts =  contactDao.getContactByPhoneAsc()
        contactLiveData.postValue(contacts)
    }

    fun contactByCreatedAtDsc() {
        val contacts =  contactDao.getContactByNameDsc()
        contactLiveData.postValue(contacts)
    }

    fun contactByCreatedatAsc() {
        val contacts =  contactDao.getContactByCreatedAtAsc()
        contactLiveData.postValue(contacts)
    }

}