package uz.pdp.exchangerates.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.pdp.exchangerates.database.entities.Currency

interface ApiService {

    @GET(".")
    fun getCurrency(): Call<List<Currency>>
}