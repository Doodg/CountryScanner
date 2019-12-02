package com.enigma.presentation.modelmapper

import com.enigma.presentation.model.CountryResponsePresentation
import com.enigma.usecase.model.CountryResponseModel

class CountryResponsePresentationMapper(
    private val translationPresentationMapper: TranslationPresentationMapper
) : PresentationMapper<CountryResponseModel, CountryResponsePresentation> {
    override fun mapFromUseCaseToPresentationModel(useCaseModel: CountryResponseModel): CountryResponsePresentation {
        return CountryResponsePresentation(
            area = useCaseModel.area,
            nativeName = useCaseModel.nativeName,
            capital = useCaseModel.capital,
            demonym = useCaseModel.demonym,
            alpha2Code = useCaseModel.alpha2Code,
            languages = useCaseModel.languages,
            borders = useCaseModel.borders,
            subregion = useCaseModel.subregion,
            callingCodes = useCaseModel.callingCodes,
            gini = useCaseModel.gini,
            relevance = useCaseModel.relevance,
            population = useCaseModel.population,
            numericCode = useCaseModel.numericCode,
            alpha3Code = useCaseModel.alpha3Code,
            topLevelDomain = useCaseModel.topLevelDomain,
            timezones = useCaseModel.timezones,
            translations = useCaseModel.translations?.let {
                translationPresentationMapper.mapFromUseCaseToPresentationModel(
                    it
                )
            },
            name = useCaseModel.name,
            altSpellings = useCaseModel.altSpellings,
            region = useCaseModel.region,
            latlng = useCaseModel.latlng,
            isFavourite = useCaseModel.isFavourite,
            currencies = useCaseModel.currencies
        )
    }

    override fun mapFromPresentationModelToUseCase(model: CountryResponsePresentation): CountryResponseModel {
        return CountryResponseModel(
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
            translations = model.translations?.let {
                translationPresentationMapper.mapFromPresentationModelToUseCase(
                    it
                )
            },
            name = model.name,
            altSpellings = model.altSpellings,
            region = model.region,
            latlng = model.latlng,
            isFavourite = model.isFavourite,
            currencies = model.currencies
        )
    }
}