package com.enigma.remote.modelmapper

import com.enigma.datadistrbuter.model.TranslationsEntity
import com.enigma.remote.model.Translations

class TranslationMapper : RemoteModelMapper<Translations, TranslationsEntity> {
    override fun mapFromRemoteToData(remoteModel: Translations): TranslationsEntity {
        return TranslationsEntity(
            de = remoteModel.de,
            ja = remoteModel.ja,
            it = remoteModel.it,
            fr = remoteModel.fr,
            es = remoteModel.es
        )
    }
}