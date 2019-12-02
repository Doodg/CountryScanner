package com.enigma.usecase.di

import com.enigma.usecase.cases.GetAllCountriesUseCase
import com.enigma.usecase.cases.GetAllFavouriteCountries
import com.enigma.usecase.cases.MarkCountryAsFavouriteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllCountriesUseCase(get(), get()) }
    factory { MarkCountryAsFavouriteUseCase(get(), get()) }
    factory { GetAllFavouriteCountries(get(),get()) }
}