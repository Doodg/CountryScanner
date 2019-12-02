package com.enigma.datadistrbuter.modelmapper

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.usecase.model.CountryResponseModel

class CountryResponseEntityMapper(private val translationsEntityMapper: TranslationsEntityMapper) :
    DataMapper<CountryResponseEntity, CountryResponseModel> {
    override fun mapFromDataToUseCaseModel(entityModel: CountryResponseEntity): CountryResponseModel {
        return CountryResponseModel(
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
            translations = entityModel.translations?.let { translationsEntityMapper.mapFromDataToUseCaseModel(it) },
            name = entityModel.name,
            altSpellings = entityModel.altSpellings,
            region = entityModel.region,
            latlng = entityModel.latlng,
            isFavourite = entityModel.isFavourite,
            currencies = entityModel.currencies
        )
    }

    override fun mapFromUseCaseModelToData(model: CountryResponseModel): CountryResponseEntity {
        return CountryResponseEntity(
            area = model.area,
            nativeName = model.nativeName,
            capital = model.capital,
            demonym = model.demonym,
            alpha2Code = model.alpha2Code,
            languages = model.languages,
            borders = model.borders,
            subregion = model.subregion,
            callingCodes = model.callingCodes,
            gini = model.gini,
            relevance = model.relevance,
            population = model.population,
            numericCode = model.numericCode,
            alpha3Code = model.alpha3Code,
            topLevelDomain = model.topLevelDomain,
            timezones = model.timezones,
            translations = model.translations?.let { translationsEntityMapper.mapFromUseCaseModelToData(it) },
            name = model.name,
            altSpellings = model.altSpellings,
            region = model.region,
            latlng = model.latlng,
            isFavourite = model.isFavourite,
            currencies = model.currencies
        )
    }
}