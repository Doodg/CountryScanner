package com.enigma.usecase.cases

import com.enigma.usecase.CountriesRepository
import com.enigma.usecase.excuter.FlowableUseCase
import com.enigma.usecase.excuter.PostExecutionThread
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Flowable

class GetAllFavouriteCountries(
    private val postExecutionThread: PostExecutionThread,
    private val countriesRepository: CountriesRepository
) : FlowableUseCase<List<CountryResponseModel>, Unit>(postExecutionThread) {
    override fun buildUseCaseFlowable(params: Unit?): Flowable<List<CountryResponseModel>> {
        return countriesRepository.getAllFavouriteCountries()
    }

}