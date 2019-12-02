package com.enigma.datadistrbuter

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.datadistrbuter.modelmapper.CountryResponseEntityMapper
import com.enigma.datadistrbuter.store.CountryDataStoreFactory
import com.enigma.usecase.CountriesRepository
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import sun.rmi.runtime.Log

class CountriesDataRepoImpl(
    private val countryDataStoreFactory: CountryDataStoreFactory,
    private val countryResponseEntityMapper: CountryResponseEntityMapper
) : CountriesRepository {
    override fun getAllFavouriteCountries(): Flowable<List<CountryResponseModel>> {
        return countryDataStoreFactory.getCacheDataStore().getAllFavouriteCountries()
            .map { countries ->
                println("hamdaSIZE${countries.size}")
                countries.map {
                    countryResponseEntityMapper.mapFromDataToUseCaseModel(it)
                }
            }
    }

    override fun saveFavouriteCountry(country: CountryResponseModel): Completable {
        return countryDataStoreFactory.getCacheDataStore()
            .saveFavouriteCountry(countryResponseEntityMapper.mapFromUseCaseModelToData(country))
    }

    override fun getAllCountries(): Observable<List<CountryResponseModel>> {
        val cachedCountries = getAllFavouriteCountries().toObservable()
        val remoteCountries = countryDataStoreFactory.getRemoteDataStore().getAllCountries().map {
            it.map {
                countryResponseEntityMapper.mapFromDataToUseCaseModel(it)
            }
        }
       return Observable.zip(remoteCountries,cachedCountries, BiFunction<List<CountryResponseModel>,List<CountryResponseModel>,List<CountryResponseModel>> { remoteCountry, cachedCountry ->
           remoteCountry.map { item -> item.isFavourite = (cachedCountry.find { it.alpha2Code == item.alpha2Code } != null)
           item}

        })


//            Observable.zip(Observable.just(country),country.alpha2Code.let {
//                getAllFavouriteCountries()
//            }, BiFunction<CountryResponseModel,CountryResponseModel,CountryResponseModel>{country,favCountry->
//                country.isFavourite = favCountry.isFavourite
//                country
//            }}

    }
}