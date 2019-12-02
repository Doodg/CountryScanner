package com.enigma.datadistrbuter.repositry

import com.enigma.datadistrbuter.model.CountryResponseEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface CountryRemote {
    fun getAllCountriesRemote(): Observable<List<CountryResponseEntity>>

}