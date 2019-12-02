package com.enigma.cache.db

import android.content.Context
import androidx.room.*
import com.enigma.cache.model.CachedCountryResponse
import com.enigma.cache.model.CachedTranslations

@Database(entities = arrayOf(CachedCountryResponse::class), version = 1)
@TypeConverters(ConverterListString::class, ConverterListFloat::class, ConverterTranslations::class)
abstract class CountryDataBaseManager : RoomDatabase() {
    //daos
    abstract fun cachedCountry(): CachedCountryDao

    companion object {
        private var roomInstance: CountryDataBaseManager? = null

        fun getInstance(context: Context): CountryDataBaseManager {
            if (roomInstance == null) {
                roomInstance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDataBaseManager::class.java,
                    DatabaseConstant.DATABASE_NAME
                ).build()
            }
            return roomInstance as CountryDataBaseManager
        }
    }
}