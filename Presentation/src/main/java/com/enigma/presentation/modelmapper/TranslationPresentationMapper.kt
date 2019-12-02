package com.enigma.presentation.modelmapper

import com.enigma.presentation.model.TranslationsPresentation
import com.enigma.usecase.model.TranslationsModel

class TranslationPresentationMapper :
    PresentationMapper<TranslationsModel, TranslationsPresentation> {
    override fun mapFromUseCaseToPresentationModel(useCaseModel: TranslationsModel): TranslationsPresentation {
        return TranslationsPresentation(
            de = useCaseModel.de,
            ja = useCaseModel.ja,
            it = useCaseModel.it,
            fr = useCaseModel.fr,
            es = useCaseModel.es
        )
    }

    override fun mapFromPresentationModelToUseCase(useCaseModel: TranslationsPresentation): TranslationsModel {
        return TranslationsModel(
            de = useCaseModel.de,
            ja = useCaseModel.ja,
            it = useCaseModel.it,
            fr = useCaseModel.fr,
            es = useCaseModel.es
        )
    }
}