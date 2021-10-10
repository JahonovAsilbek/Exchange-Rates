package uz.pdp.exchangerates.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.retrofit.ApiClient

class CurrencyViewModel : ViewModel() {

    private var liveData = MutableLiveData<List<Currency>>()

    fun getCurrency(): LiveData<List<Currency>> {
        ApiClient.apiService.getCurrency().enqueue(object : Callback<List<Currency>> {
            override fun onResponse(
                call: Call<List<Currency>>,
                response: Response<List<Currency>>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()

                }
            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {}
        })

        return liveData
    }
}