package com.enigma.cache.di

import com.enigma.cache.CountriesCacheImpl
import com.enigma.cache.modelmapper.CachedCountryResponseMapper
import com.enigma.cache.modelmapper.CachedTranslationsMapper
import com.enigma.cache.db.CountryDataBaseManager
import com.enigma.datadistrbuter.repositry.CountryCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module {
    /* Mappers */
    single { CachedTranslationsMapper() }
    single { CachedCountryResponseMapper(get()) }
    /*Room Datanse */
    single { CountryDataBaseManager.getInstance(context = androidContext()) }

    /* Repo Impl */
    single<CountryCache> { CountriesCacheImpl(get(), get()) }
}