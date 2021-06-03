package com.app.countrytestappkotlin.apisrvice

import com.app.countrytestappkotlin.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("TssAppServices/http/CallServiceGet?data={\"method\":0,\"pwd\":\"test\",\"uname\":\"test\"}")
    fun getCountryList(): Call<Country>
}