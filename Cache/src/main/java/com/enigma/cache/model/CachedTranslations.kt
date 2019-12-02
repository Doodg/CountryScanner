package com.enigma.cache.model

import androidx.room.Entity
import com.enigma.cache.db.DatabaseConstant

data class CachedTranslations(

    val de: String? = null,

    val ja: String? = null,

    val it: String? = null,

    val fr: String? = null,

    val es: String? = null
) : CachedModel