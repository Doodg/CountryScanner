package com.enigma.presentation.di

import com.enigma.presentation.PostExecutionThreadImp
import com.enigma.presentation.modelmapper.CountryResponsePresentationMapper
import com.enigma.presentation.modelmapper.TranslationPresentationMapper
import com.enigma.presentation.viewmodel.CountriesViewModel
import com.enigma.presentation.viewmodel.FavouriteCountryViewModel
import com.enigma.usecase.excuter.PostExecutionThread
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    //Mappers
    single { TranslationPresentationMapper() }
    single { CountryResponsePresentationMapper(get()) }
    single<PostExecutionThread> { PostExecutionThreadImp() }


    //ViewModels
    viewModel { CountriesViewModel(get(), get(), get()) }
    viewModel { FavouriteCountryViewModel(get(),get()) }
}