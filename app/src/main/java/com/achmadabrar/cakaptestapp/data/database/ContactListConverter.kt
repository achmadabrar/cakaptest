package com.achmadabrar.cakaptestapp.data.database

import androidx.room.TypeConverter
import com.achmadabrar.cakaptestapp.data.models.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContactListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<Contact>): String {
            val type = object : TypeToken<MutableList<Contact>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<Contact> {
            val type = object : TypeToken<MutableList<Contact>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}