package com.app.countrytestappkotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.app.countrytestappkotlin.apisrvice.ApiClient
import com.app.countrytestappkotlin.apisrvice.ApiInterface
import com.app.countrytestappkotlin.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var countrySpinner: Spinner? = null
    var text_country_code: TextView? = null
    var progressDialog: ProgressDialog? = null
    var SelectedCountryName: String? = null
    var countryResponses: List<Country.CountryResponse>? = null
    var adapter: ArrayAdapter<String>? = null
    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countrySpinner = findViewById(R.id.country_spinner)
        text_country_code = findViewById(R.id.text_country_code)
        countrySpinner?.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val selected_val = countrySpinner?.getSelectedItem().toString()
                if (selected_val == AppController.instance.countryList?.get(0)?.name) {
                    text_country_code?.setText(AppController.instance.countryList?.get(0)?.countryCode)
                } else if (selected_val ==  AppController.instance.countryList?.get(1)?.name) {
                    text_country_code?.setText(AppController.instance.countryList?.get(1)?.countryCode)
                } else if (selected_val ==  AppController.instance.countryList?.get(2)?.name) {
                    text_country_code?.setText( AppController.instance.countryList?.get(2)?.countryCode)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        getSpinnerData()

    }

    private fun getSpinnerData() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Loading Data..")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
        val service: ApiInterface = ApiClient.getRetrofitInstance()!!.create(ApiInterface::class.java)
        val call: Call<Country> = service.getCountryList()
        call.enqueue(object : Callback<Country> {
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                countryResponses = response.body()!!.countryResponse
                val items = arrayOfNulls<String>(countryResponses!!.size)
                for (i in countryResponses!!.indices) {
                    items[i] = countryResponses!![i].name
                }
                adapter =
                    ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, items)
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                countrySpinner!!.adapter = adapter
                AppController.instance.countryList= response.body()!!.countryResponse

                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
                progressDialog!!.dismiss()
            }
        })
    }
}