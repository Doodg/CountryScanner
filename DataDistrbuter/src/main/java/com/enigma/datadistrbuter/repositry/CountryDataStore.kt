package com.enigma.datadistrbuter.repositry

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface CountryDataStore {
    fun getAllCountries(): Observable<List<CountryResponseEntity>>
    fun saveFavouriteCountry(country: CountryResponseEntity): Completable
    fun getAllFavouriteCountries(): Flowable<List<CountryResponseEntity>>


}