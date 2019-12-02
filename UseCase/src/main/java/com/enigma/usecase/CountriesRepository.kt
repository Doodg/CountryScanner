package com.enigma.usecase

import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface CountriesRepository {
    fun getAllCountries(): Observable<List<CountryResponseModel>>
    fun saveFavouriteCountry(country: CountryResponseModel): Completable
    fun getAllFavouriteCountries(): Flowable<List<CountryResponseModel>>

}