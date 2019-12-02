package com.enigma.remote.di

import com.enigma.datadistrbuter.repositry.CountryRemote
import com.enigma.remote.CountryRemoteImpl
import com.enigma.remote.modelmapper.CountryResponseMapper
import com.enigma.remote.modelmapper.TranslationMapper
import com.enigma.remote.retrofit.CountriesService
import com.enigma.remote.retrofit.RetrofitClient
import org.koin.dsl.module


val remoteModule = module {

    //mappers
    single { TranslationMapper() }
    single { CountryResponseMapper(get()) }

    //Remotes Impl
    single<CountryRemote> { CountryRemoteImpl(get(), get()) }


    //Retrofit services
    single { RetrofitClient.retrofit.create(CountriesService::class.java) }
}