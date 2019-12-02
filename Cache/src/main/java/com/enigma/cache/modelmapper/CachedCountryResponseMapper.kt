package com.enigma.cache.modelmapper

import com.enigma.cache.model.CachedCountryResponse
import com.enigma.datadistrbuter.model.CountryResponseEntity

class CachedCountryResponseMapper(private var translationMapper: CachedTranslationsMapper) :
    CachedMapper<CachedCountryResponse, CountryResponseEntity> {

    override fun mapFromCached(cachedModel: CachedCountryResponse): CountryResponseEntity {
        return CountryResponseEntity(
            area = cachedModel.area,
            nativeName = cachedModel.nativeName,
            capital = cachedModel.capital,
            demonym = cachedModel.demonym,
            alpha2Code = cachedModel.alpha2Code,
            languages = cachedModel.languages,
            borders = cachedModel.borders,
            subregion = cachedModel.subregion,
            callingCodes = cachedModel.callingCodes,
            gini = cachedModel.gini,
            relevance = cachedModel.relevance,
            population = cachedModel.population,
            numericCode = cachedModel.numericCode,
            alpha3Code = cachedModel.alpha3Code,
            topLevelDomain = cachedModel.topLevelDomain,
            timezones = cachedModel.timezones,
            translations = cachedModel.translations?.let { translationMapper.mapFromCached(it) },
            name = cachedModel.name,
            altSpellings = cachedModel.altSpellings,
            region = cachedModel.region,
            latlng = cachedModel.latlng,
            isFavourite = cachedModel.isFavourite,
            currencies = cachedModel.currencies
        )
    }

    override fun mapToCached(entityModel: CountryResponseEntity): CachedCountryResponse {
        return CachedCountryResponse(
            area = entityModel.area,
            nativeName = entityModel.nativeName,
            capital = entityModel.capital,
            demonym = entityModel.demonym,
            alpha2Code = entityModel.alpha2Code,
            languages = entityModel.languages,
            borders = entityModel.borders,
            subregion = entityModel.subregion,
            callingCodes = entityModel.callingCodes,
            gini = entityModel.gini,
            relevance = entityModel.relevance,
            population = entityModel.population,
            numericCode = entityModel.numericCode,
            alpha3Code = entityModel.alpha3Code,
            topLevelDomain = entityModel.topLevelDomain,
            timezones = entityModel.timezones,
            translations = entityModel.translations?.let { translationMapper.mapToCached(it) },
            name = entityModel.name,
            altSpellings = entityModel.altSpellings,
            region = entityModel.region,
            latlng = entityModel.latlng,
            isFavourite = entityModel.isFavourite,
            currencies = entityModel.currencies
        )
    }
}