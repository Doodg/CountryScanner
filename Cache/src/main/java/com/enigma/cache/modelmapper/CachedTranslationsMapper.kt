package com.enigma.cache.modelmapper

import com.enigma.cache.model.CachedTranslations
import com.enigma.datadistrbuter.model.TranslationsEntity

class CachedTranslationsMapper : CachedMapper<CachedTranslations, TranslationsEntity> {
    override fun mapFromCached(cachedModel: CachedTranslations): TranslationsEntity {
        return TranslationsEntity(
            de = cachedModel.de,
            ja = cachedModel.ja,
            it = cachedModel.it,
            fr = cachedModel.fr,
            es = cachedModel.es
        )
    }

    override fun mapToCached(entityModel: TranslationsEntity): CachedTranslations {
        return CachedTranslations(
            de = entityModel.de,
            ja = entityModel.ja,
            it = entityModel.it,
            fr = entityModel.fr,
            es = entityModel.es
        )
    }
}