package com.enigma.remote

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.datadistrbuter.repositry.CountryRemote
import com.enigma.remote.modelmapper.CountryResponseMapper
import com.enigma.remote.retrofit.CountriesService
import io.reactivex.Observable

class CountryRemoteImpl(
    private val countriesService: CountriesService,
    private val countryResponseMapper: CountryResponseMapper
) : CountryRemote {
    override fun getAllCountriesRemote(): Observable<List<CountryResponseEntity>> {
        return countriesService.getAllCountriesAPI().map {
            it.map {
                countryResponseMapper.mapFromRemoteToData(it)

            }
        }
    }

}