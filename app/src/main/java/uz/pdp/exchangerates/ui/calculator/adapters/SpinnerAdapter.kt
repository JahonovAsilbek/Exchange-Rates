package uz.pdp.exchangerates.ui.calculator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import uz.pdp.exchangerates.R
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.ItemSpinnerBinding

class SpinnerAdapter(var data: ArrayList<Currency>) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Currency {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = ItemSpinnerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        Picasso.get()
            .load("https://nbu.uz/local/templates/nbu/images/flags/${data[position].code}.png")
            .error(R.drawable.uzbekistan)
            .into(view.image)

        view.code.text = data[position].code
        return view.root
    }
}