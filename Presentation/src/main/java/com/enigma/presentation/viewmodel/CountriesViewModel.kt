package com.enigma.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.enigma.presentation.Resource
import com.enigma.presentation.model.CountryResponsePresentation
import com.enigma.presentation.modelmapper.CountryResponsePresentationMapper
import com.enigma.usecase.cases.GetAllCountriesUseCase
import com.enigma.usecase.cases.MarkCountryAsFavouriteUseCase
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver

class CountriesViewModel(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val markCountryAsFavouriteUseCase: MarkCountryAsFavouriteUseCase,
    private val countryResponsePresentationMapper: CountryResponsePresentationMapper
) : ViewModel() {
    private val countriesMutableLiveData: MutableLiveData<Resource<List<CountryResponsePresentation>>> =
        MutableLiveData()

    fun executeGetAllCountriesUseCase() {
        countriesMutableLiveData.postValue(Resource.loading())
        getAllCountriesUseCase.execute(GetAllCountriesSubscriber())
    }

    inner class GetAllCountriesSubscriber : DisposableObserver<List<CountryResponseModel>>() {
        override fun onComplete() {
        }

        override fun onNext(countries: List<CountryResponseModel>) {
            countriesMutableLiveData.value = Resource.success(countries.map { countryResponsePresentationMapper
                        .mapFromUseCaseToPresentationModel(it) })
        }
        override fun onError(e: Throwable) {
            countriesMutableLiveData.postValue(Resource.error(e))
        }
    }

    fun observeOnGetAllCountries(
        owner: LifecycleOwner,
        countryValue: Observer<Resource<List<CountryResponsePresentation>>>
    ) {
        countriesMutableLiveData.observe(owner, countryValue)
    }

    fun markAsFavourite(country: CountryResponsePresentation) {

        markCountryAsFavouriteUseCase.execute(MarkCountryFavouriteSubscriber(), MarkCountryAsFavouriteUseCase.Param( countryResponsePresentationMapper.mapFromPresentationModelToUseCase(country)))
    }

    inner class MarkCountryFavouriteSubscriber() : DisposableCompletableObserver() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun onCleared() {
        getAllCountriesUseCase.dispose()
        super.onCleared()
    }

}