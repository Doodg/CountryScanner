package com.enigma.cache.db

import androidx.room.TypeConverter
import com.enigma.cache.model.CachedTranslations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterTranslations {
    @TypeConverter
    fun convertToGson(value: String): CachedTranslations {
        val type = object : TypeToken<CachedTranslations>() {

        }.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun convertToString(cachedTranslations: CachedTranslations): String {
        return Gson().toJson(cachedTranslations)
    }
}