package uz.pdp.exchangerates.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_calculator.*
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.FragmentCalculatorBinding
import uz.pdp.exchangerates.ui.calculator.adapters.SpinnerAdapter
import uz.pdp.exchangerates.viewmodel.CurrencyViewModel

class CalculatorFragment : Fragment() {

    private lateinit var currencyViewModel: CurrencyViewModel
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    lateinit var data: ArrayList<Currency>
    lateinit var spinnerAdapter1: SpinnerAdapter
    lateinit var spinnerAdapter2: SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Pro Valyuta kurslari"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currencyViewModel =
            ViewModelProvider(this).get(CurrencyViewModel::class.java)

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        currencyViewModel.getCurrency().observe(viewLifecycleOwner, {
            data = ArrayList()
            data.add(Currency("1", "UZS", null, "1", "1", ""))
            data.addAll(it as ArrayList<Currency>)
            spinnerAdapter1 = SpinnerAdapter(data)
            spinnerAdapter2 = SpinnerAdapter(data)
            binding.spinner1.adapter = spinnerAdapter1
            binding.spinner2.adapter = spinnerAdapter2
            binding.spinner1.setSelection(1)

            var nominal1 = 0.0

            binding.edit.addTextChangedListener {
                if (data[binding.spinner1.selectedItemPosition].nbu_buy_price!!.isNotEmpty()) {
                    nominal1 = data[spinner_1.selectedItemPosition].cb_price!!.toDouble()


                }
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}