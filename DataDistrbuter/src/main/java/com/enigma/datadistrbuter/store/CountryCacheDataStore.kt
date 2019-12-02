package com.enigma.datadistrbuter.store

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.datadistrbuter.repositry.CountryCache
import com.enigma.datadistrbuter.repositry.CountryDataStore
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException

class CountryCacheDataStore(private val countryCache: CountryCache) : CountryDataStore {
    override fun getAllFavouriteCountries(): Flowable<List<CountryResponseEntity>> {
        return countryCache.getAllFavouriteCountries()
    }

    override fun getAllCountries(): Observable<List<CountryResponseEntity>> {
        throw UnsupportedOperationException("Get All Countries Is not support Here")
    }

    override fun saveFavouriteCountry(country: CountryResponseEntity): Completable {
        return countryCache.saveFavouriteCountry(country)
    }
}