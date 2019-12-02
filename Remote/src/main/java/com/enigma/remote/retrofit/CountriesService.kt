package com.enigma.remote.retrofit

import com.enigma.remote.model.CountryResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CountriesService {

    @GET("all")
    fun getAllCountriesAPI(): Observable<List<CountryResponse>>

}