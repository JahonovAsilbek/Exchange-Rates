package uz.pdp.exchangerates.ui.currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.ItemCurrencyBinding

class CurrencyAdapter(var data: List<Currency>, var onItemClick: OnItemClick) :
    RecyclerView.Adapter<CurrencyAdapter.VH>() {

    inner class VH(var itemCurrencyBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(itemCurrencyBinding.root) {

        fun onBind(currency: Currency, onItemClick: OnItemClick) {

            Picasso.get()
                .load("https://nbu.uz/local/templates/nbu/images/flags/${currency.code}.png")
                .into(itemCurrencyBinding.image)

            if (currency.nbu_buy_price!!.isNotEmpty()) {
                itemCurrencyBinding.buy.text = currency.nbu_buy_price
                itemCurrencyBinding.sell.text = currency.nbu_cell_price
            } else {
                itemCurrencyBinding.buy.text = currency.cb_price
                itemCurrencyBinding.sell.text = currency.cb_price
            }
            itemCurrencyBinding.code.text = currency.code

            itemView.setOnClickListener {
                onItemClick.onCLick(currency)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(data[position], onItemClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateList(temp: MutableList<Currency>) {
        data = temp
        notifyDataSetChanged()
    }

    interface OnItemClick {
        fun onCLick(currency: Currency)
    }
}