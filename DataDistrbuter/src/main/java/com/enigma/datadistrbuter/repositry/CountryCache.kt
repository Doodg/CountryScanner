package com.enigma.datadistrbuter.repositry

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface CountryCache {
    fun saveFavouriteCountry(countryResponseEntity: CountryResponseEntity): Completable
    fun getAllFavouriteCountries(): Flowable<List<CountryResponseEntity>>

}