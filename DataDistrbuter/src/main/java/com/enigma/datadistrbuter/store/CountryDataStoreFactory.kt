package com.enigma.datadistrbuter.store

import com.enigma.datadistrbuter.repositry.CountryDataStore

class CountryDataStoreFactory(
    private val countryRemoteDataSotre: CountryRemoteDataStore,
    private val countryCacheDataStore: CountryCacheDataStore
) {

    fun getRemoteDataStore(): CountryDataStore {
        return countryRemoteDataSotre
    }

    fun getCacheDataStore(): CountryDataStore {
        return countryCacheDataStore
    }
}