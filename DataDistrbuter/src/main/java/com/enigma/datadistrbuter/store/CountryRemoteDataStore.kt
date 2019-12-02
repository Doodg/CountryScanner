package com.enigma.datadistrbuter.store

import com.enigma.datadistrbuter.repositry.CountryDataStore
import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.datadistrbuter.repositry.CountryRemote
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException

class CountryRemoteDataStore(private val countryRemoteDataSotre: CountryRemote) :
    CountryDataStore {
    override fun getAllFavouriteCountries(): Flowable<List<CountryResponseEntity>> {
        throw UnsupportedOperationException("Get favourite  Countries Is not support ")
    }

    override fun saveFavouriteCountry(country: CountryResponseEntity): Completable {
        throw UnsupportedOperationException("save favourite  Countries Is not support ")
    }

    override fun getAllCountries(): Observable<List<CountryResponseEntity>> {
        return countryRemoteDataSotre.getAllCountriesRemote()
    }

}