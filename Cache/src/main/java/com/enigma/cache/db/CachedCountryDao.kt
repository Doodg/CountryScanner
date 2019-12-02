package com.enigma.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enigma.cache.model.CachedCountryResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface CachedCountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteCountry(country: CachedCountryResponse): Completable

    @Query("SELECT * FROM ${DatabaseConstant.TABLE_NAME_COUNTRY} WHERE ${DatabaseConstant.COLUMN_COUNTRY_IS_FAVOURITE}=1")
    fun getAllFavouritesCountries(): Flowable<List<CachedCountryResponse>>

    @Query("DELETE  FROM ${DatabaseConstant.TABLE_NAME_COUNTRY} WHERE ${DatabaseConstant.COLUMN_COUNTRY_CODE}=:countryCode")
    fun removeFavouriteCountry(countryCode: String): Completable

    @Query("SELECT * FROM ${DatabaseConstant.TABLE_NAME_COUNTRY} WHERE ${DatabaseConstant.COLUMN_COUNTRY_NAME}=:name OR  ${DatabaseConstant.COLUMN_COUNTRY_NAIVE_NAME}=:name")
    fun searchCountry(name: String): Observable<List<CachedCountryResponse>>
}