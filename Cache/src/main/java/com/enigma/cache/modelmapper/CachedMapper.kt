package com.enigma.cache.modelmapper

import com.enigma.cache.model.CachedModel
import com.enigma.datadistrbuter.model.EntityModel

interface CachedMapper<C : CachedModel, E : EntityModel> {
    fun mapFromCached(cachedModel: C): E
    fun mapToCached(entityModel: E): C
}