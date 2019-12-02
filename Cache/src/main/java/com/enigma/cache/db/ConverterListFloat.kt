package com.enigma.cache.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONArray


class ConverterListFloat {

//    @TypeConverter
//    fun fromListOfFloats(list: ArrayList<Float>?): String {
//        return list?.joinToString(separator = ";") { it.toString() } ?: ""
//    }
//
//    @TypeConverter
//    fun toListOfFloats(string: String?): List<Float> {
//        return ArrayList(string?.split(";")?.mapNotNull { it.toFloatOrNull() } ?: emptyList())
//    }
    @TypeConverter
    fun fromListOfFloats(value:String): ArrayList<Float> {
        val listType = object : TypeToken< ArrayList<Float>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toListOfFloats(list:  ArrayList<Float>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}