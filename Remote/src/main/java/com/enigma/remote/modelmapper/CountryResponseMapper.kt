package com.enigma.remote.modelmapper

import com.enigma.datadistrbuter.model.CountryResponseEntity
import com.enigma.remote.model.CountryResponse

/*
*
* this class is response for mapping the oject we got from the API to the next LEVEL
* here we map the object from remote level to data level
* @NOTE any sub object inside the main object,
* is need a seprate mapper like I did here with @translation object
*/
class CountryResponseMapper(private var translationMapper: TranslationMapper) :
    RemoteModelMapper<CountryResponse, CountryResponseEntity> {
    override fun mapFromRemoteToData(remoteModel: CountryResponse): CountryResponseEntity {
        return CountryResponseEntity(
            area = remoteModel.area,
            nativeName = remoteModel.nativeName,
            capital = remoteModel.capital,
            demonym = remoteModel.demonym,
            alpha2Code = remoteModel.alpha2Code,
            languages = remoteModel.languages,
            borders = remoteModel.borders,
            subregion = remoteModel.subregion,
            callingCodes = remoteModel.callingCodes,
            gini = remoteModel.gini,
            relevance = remoteModel.relevance,
            population = remoteModel.population,
            numericCode = remoteModel.numericCode,
            alpha3Code = remoteModel.alpha3Code,
            topLevelDomain = remoteModel.topLevelDomain,
            timezones = remoteModel.timezones,
            translations = remoteModel.translations?.let { translationMapper.mapFromRemoteToData(it) },
            name = remoteModel.name,
            altSpellings = remoteModel.altSpellings,
            region = remoteModel.region,
            latlng = remoteModel.latlng,
            isFavourite = remoteModel.isFavourite,
            currencies = remoteModel.currencies
        )
    }

}