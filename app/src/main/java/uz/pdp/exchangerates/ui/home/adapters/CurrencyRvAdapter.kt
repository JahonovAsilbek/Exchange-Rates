package uz.pdp.exchangerates.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.ItemCurrency2Binding

class CurrencyRvAdapter() :
    RecyclerView.Adapter<CurrencyRvAdapter.VH>() {

    private lateinit var data: ArrayList<Currency>

    fun setAdapter(data: ArrayList<Currency>) {
        this.data = data
    }

    inner class VH(var itemCurrency2Binding: ItemCurrency2Binding) :
        RecyclerView.ViewHolder(itemCurrency2Binding.root) {

        fun onBind(currency: Currency) {
            itemCurrency2Binding.currency = currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCurrency2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size
}