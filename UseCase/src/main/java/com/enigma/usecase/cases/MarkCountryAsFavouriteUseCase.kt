package com.enigma.usecase.cases

import com.enigma.usecase.CountriesRepository
import com.enigma.usecase.excuter.CompletableUseCase
import com.enigma.usecase.excuter.PostExecutionThread
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Completable

class MarkCountryAsFavouriteUseCase(
    private val postExecutionThread: PostExecutionThread,
    private val countriesRepository: CountriesRepository
) : CompletableUseCase<MarkCountryAsFavouriteUseCase.Param>(postExecutionThread) {
    override fun buildCompletableUseCase(params: MarkCountryAsFavouriteUseCase.Param?): Completable {
        if (params == null) {
            throw IllegalArgumentException("Params can not be null")
        }
        return countriesRepository.saveFavouriteCountry(params.country)
    }

    data class Param(val country: CountryResponseModel) {
        companion object {
            fun saveCountry(country: CountryResponseModel): Param {
                return Param(country = country)
            }
        }
    }
}