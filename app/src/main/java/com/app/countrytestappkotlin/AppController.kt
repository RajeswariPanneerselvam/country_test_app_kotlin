package com.app.countrytestappkotlin

import android.app.Application
import com.app.countrytestappkotlin.model.Country

class AppController: Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
    companion object {
        lateinit  var instance: AppController
            private set
    }

    var countryList: List<Country.CountryResponse>?=null

}