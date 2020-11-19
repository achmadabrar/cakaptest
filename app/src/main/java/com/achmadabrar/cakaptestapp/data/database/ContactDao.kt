package com.achmadabrar.cakaptestapp.data.database

import androidx.room.*
import com.achmadabrar.cakaptestapp.data.models.Contact

@Dao
@TypeConverters(ContactListConverter::class)
interface ContactDao {

    @Query("SELECT * from contact_table ORDER BY name ASC")
    fun getContactByNameAsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY name DESC")
    fun getContactByNameDsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY email ASC")
    fun getContactByEmailAsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY email DESC")
    fun getContactByEmailDsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY phoneNumber ASC")
    fun getContactByPhoneAsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY phoneNumber DESC")
    fun getContactByPhoneDsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY createdAt ASC")
    fun getContactByCreatedAtAsc(): List<Contact>?

    @Query("SELECT * from contact_table ORDER BY createdAt DESC")
    fun getContactByCreatedAtDsc(): List<Contact>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListContact(contacts: List<Contact>)
}