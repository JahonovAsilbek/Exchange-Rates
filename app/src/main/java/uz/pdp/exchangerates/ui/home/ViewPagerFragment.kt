package uz.pdp.exchangerates.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.FragmentViewPagerBinding

private const val ARG_PARAM1 = "param1"

class ViewPagerFragment : Fragment() {

    private var currency: Currency? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currency = it.getSerializable(ARG_PARAM1) as Currency
        }
    }

    lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(layoutInflater)

        if (currency?.nbu_buy_price!!.isNotEmpty()) {
            binding.buy.text = currency?.nbu_buy_price
            binding.sell.text = currency?.nbu_cell_price
        } else {
            binding.buy.text = currency?.cb_price
            binding.sell.text = currency?.cb_price
        }
        binding.date.text = currency?.date!!.substring(0, currency?.date!!.indexOf(" ") + 1)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(currency: Currency) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, currency)
                }
            }
    }
}