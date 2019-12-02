package com.enigma.cache.model

import androidx.room.*
import com.enigma.cache.db.ConverterListFloat
import com.enigma.cache.db.ConverterTranslations
import com.enigma.cache.db.DatabaseConstant

@Entity(tableName = DatabaseConstant.TABLE_NAME_COUNTRY)
data class CachedCountryResponse(

    var area: Float? = null,
    @ColumnInfo(name = DatabaseConstant.COLUMN_COUNTRY_NAIVE_NAME)
    var nativeName: String? = null,

    var capital: String? = null,

    var demonym: String? = null,
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstant.COLUMN_COUNTRY_CODE)
    var alpha2Code: String,

    var languages: List<String?>? = null,

    var borders: List<String?>? = null,

    var subregion: String? = null,

    var callingCodes: List<String?>? = null,

    var gini: Double? = null,

    var relevance: String? = null,

    var population: Int? = null,

    var numericCode: String? = null,

    var alpha3Code: String? = null,

    var topLevelDomain: List<String?>? = null,

    var timezones: List<String?>? = null,

    @TypeConverters(ConverterTranslations::class)
    var translations: CachedTranslations? = null,

    @ColumnInfo(name = DatabaseConstant.COLUMN_COUNTRY_NAME)
    var name: String? = null,

    var altSpellings: List<String?>? = null,

    var region: String? = null,
    @TypeConverters(ConverterListFloat::class)
    var latlng: ArrayList<Float?>? = null,
    @ColumnInfo(name = DatabaseConstant.COLUMN_COUNTRY_IS_FAVOURITE)
    var isFavourite: Boolean = false,
    var currencies: List<String?>? = null
) : CachedModel