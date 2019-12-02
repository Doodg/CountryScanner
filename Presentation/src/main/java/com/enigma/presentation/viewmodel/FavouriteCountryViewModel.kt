package com.enigma.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.enigma.presentation.Resource
import com.enigma.presentation.model.CountryResponsePresentation
import com.enigma.presentation.modelmapper.CountryResponsePresentationMapper
import com.enigma.usecase.cases.GetAllFavouriteCountries
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.subscribers.DisposableSubscriber

class FavouriteCountryViewModel(
    private val getAllFavouriteCountries: GetAllFavouriteCountries,
    private val countryResponsePresentationMapper: CountryResponsePresentationMapper
) : ViewModel() {
    private val favouriteCountriesLiveData: MutableLiveData<Resource<List<CountryResponsePresentation>>> =
        MutableLiveData()

    fun retrieveFavouriteCountries() {
        favouriteCountriesLiveData.postValue(Resource.loading())
        getAllFavouriteCountries.execute(GetFavouriteCountries())
    }
    fun observeOnGetFavouritCountries(
        owner: LifecycleOwner,
        countryValue: Observer<Resource<List<CountryResponsePresentation>>>
    ) {
        favouriteCountriesLiveData.observe(owner, countryValue)
    }
    inner class GetFavouriteCountries : DisposableSubscriber<List<CountryResponseModel>>() {
        override fun onComplete() {
        }

        override fun onNext(countries: List<CountryResponseModel>) {
            val mappedToPresentation = countries.map {
                countryResponsePresentationMapper.mapFromUseCaseToPresentationModel(it)
            }
            favouriteCountriesLiveData.postValue(Resource.success(mappedToPresentation))
        }

        override fun onError(e: Throwable?) {
            favouriteCountriesLiveData.postValue(Resource.error(e))
        }
    }

    override fun onCleared() {
        getAllFavouriteCountries.dispose()
        super.onCleared()
    }
}