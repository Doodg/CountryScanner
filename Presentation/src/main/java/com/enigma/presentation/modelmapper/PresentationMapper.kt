package com.enigma.presentation.modelmapper

import com.enigma.presentation.model.PresentationModel
import com.enigma.usecase.model.UsecaseModel


interface PresentationMapper<U : UsecaseModel, P : PresentationModel> {
    fun mapFromUseCaseToPresentationModel(useCaseModel: U): P
    fun mapFromPresentationModelToUseCase(presentationModel: P): U
}