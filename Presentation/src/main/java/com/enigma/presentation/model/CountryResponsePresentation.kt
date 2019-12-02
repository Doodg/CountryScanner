package com.enigma.presentation.model

import java.io.Serializable

data class CountryResponsePresentation(

    val area: Float? = null,

    val nativeName: String? = null,

    val capital: String? = null,

    val demonym: String? = null,

    val alpha2Code: String,

    val languages: List<String?>? = null,

    val borders: List<String?>? = null,

    val subregion: String? = null,

    val callingCodes: List<String?>? = null,

    val gini: Double? = null,

    val relevance: String? = null,

    val population: Int? = null,

    val numericCode: String? = null,

    val alpha3Code: String? = null,

    val topLevelDomain: List<String?>? = null,

    val timezones: List<String?>? = null,

    val translations: TranslationsPresentation? = null,

    val name: String? = null,

    val altSpellings: List<String?>? = null,

    val region: String? = null,

    val latlng: ArrayList<Float?>? = null,

    var isFavourite: Boolean = false,

    val currencies: List<String?>? = null
) : PresentationModel, Serializable