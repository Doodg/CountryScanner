package com.enigma.usecase.cases

import com.enigma.usecase.CountriesRepository
import com.enigma.usecase.excuter.ObservableUseCase
import com.enigma.usecase.excuter.PostExecutionThread
import com.enigma.usecase.model.CountryResponseModel
import io.reactivex.Observable

class GetAllCountriesUseCase(
    private val postExecutionThread: PostExecutionThread,
    private val countriesRepository: CountriesRepository
) : ObservableUseCase<List<CountryResponseModel>, Unit>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit?): Observable<List<CountryResponseModel>> {
        return countriesRepository.getAllCountries()
    }
}