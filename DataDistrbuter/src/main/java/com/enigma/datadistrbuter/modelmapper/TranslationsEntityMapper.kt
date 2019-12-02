package com.enigma.datadistrbuter.modelmapper

import com.enigma.datadistrbuter.model.TranslationsEntity
import com.enigma.usecase.model.TranslationsModel

class TranslationsEntityMapper :
    DataMapper<TranslationsEntity, TranslationsModel> {

    override fun mapFromDataToUseCaseModel(entityModel: TranslationsEntity): TranslationsModel {
        return TranslationsModel(
            de = entityModel.de,
            ja = entityModel.ja,
            it = entityModel.it,
            fr = entityModel.fr,
            es = entityModel.es
        )
    }

    override fun mapFromUseCaseModelToData(entityModel: TranslationsModel): TranslationsEntity {
        return TranslationsEntity(
            de = entityModel.de,
            ja = entityModel.ja,
            it = entityModel.it,
            fr = entityModel.fr,
            es = entityModel.es
        )
    }
}