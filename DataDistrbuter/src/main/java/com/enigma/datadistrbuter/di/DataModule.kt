package com.enigma.datadistrbuter.di

import com.enigma.datadistrbuter.CountriesDataRepoImpl
import com.enigma.datadistrbuter.modelmapper.CountryResponseEntityMapper
import com.enigma.datadistrbuter.modelmapper.TranslationsEntityMapper
import com.enigma.datadistrbuter.store.CountryCacheDataStore
import com.enigma.datadistrbuter.store.CountryDataStoreFactory
import com.enigma.datadistrbuter.store.CountryRemoteDataStore
import com.enigma.usecase.CountriesRepository
import org.koin.dsl.module

val dataModule = module {

    //Mappers
    single { TranslationsEntityMapper() }
    single { CountryResponseEntityMapper(get()) }

    //DataStores
    single { CountryRemoteDataStore(get()) }
    single { CountryCacheDataStore(get()) }
    single { CountryDataStoreFactory(get(), get()) }
    single<CountriesRepository> { CountriesDataRepoImpl(get(), get()) }
}