package uz.pdp.exchangerates.ui.currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.exchangerates.R
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.ItemCurrencyBinding

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.VH>() {

    lateinit var data: ArrayList<Currency>
    private lateinit var onItemClick: OnItemClick

    fun setAdapter(data: ArrayList<Currency>, onItemClick: OnItemClick) {
        this.data = data
        this.onItemClick = onItemClick
    }

    inner class VH(var itemCurrencyBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(itemCurrencyBinding.root) {

        fun onBind(currency: Currency, onItemClick: OnItemClick) {
            itemCurrencyBinding.currency = currency
            itemCurrencyBinding.url =
                "https://nbu.uz/local/templates/nbu/images/flags/${currency.code}.png"

            itemView.setOnClickListener {
                onItemClick.onCLick(currency)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<ItemCurrencyBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_currency, parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(data[position], onItemClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateList(temp: MutableList<Currency>) {
        data = temp as ArrayList<Currency>
        notifyDataSetChanged()
    }

    interface OnItemClick {
        fun onCLick(currency: Currency)
    }
}