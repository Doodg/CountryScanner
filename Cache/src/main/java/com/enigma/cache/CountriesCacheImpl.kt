package com.enigma.cache

import com.enigma.cache.db.CountryDataBaseManager
import com.enigma.cache.modelmapper.CachedCountryResponseMapper
import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.datadistrbuter.repositry.CountryCache
import io.reactivex.Completable
import io.reactivex.Flowable

class CountriesCacheImpl(
    private val countryDataBaseManager: CountryDataBaseManager,
    private var cachedCountryResponseMapper: CachedCountryResponseMapper
) : CountryCache {
    override fun getAllFavouriteCountries(): Flowable<List<CountryResponseEntity>> {
        return countryDataBaseManager.cachedCountry().getAllFavouritesCountries().map {listOfCachedCountries->
            listOfCachedCountries.map { cachedCountry->
                cachedCountryResponseMapper.mapFromCached(cachedCountry)
            }
        }
    }

    override fun saveFavouriteCountry(countryResponseEntity: CountryResponseEntity): Completable {
        return countryDataBaseManager.cachedCountry().insertFavouriteCountry(country = cachedCountryResponseMapper.mapToCached(countryResponseEntity))
    }

}