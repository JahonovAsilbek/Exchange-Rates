package uz.pdp.exchangerates.ui.currency

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class MyHandler {

    fun calculatorClick(view: View) {

    }
}

@BindingAdapter("app:url")
fun getPhoto(imageView: ImageView, url: String) {
    Picasso.get().load(url).into(imageView)
}