package com.app.countrytestappkotlin.model

class Country {
    var resultCode: String? = null
    var desc: String? = null
    var countryResponse: List<CountryResponse>? = null


    class CountryResponse {
        var id = 0
        var name: String? = null
        var isActive: Boolean? = null
        var createdAt: CreatedAt? = null
        var isDeleted: Boolean? = null
        var countryCode: String? = null
    }
}