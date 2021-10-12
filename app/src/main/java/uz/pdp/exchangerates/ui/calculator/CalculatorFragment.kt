package uz.pdp.exchangerates.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Pro Valyuta kurslari"
        arguments?.let {
            position = it.getInt("position")
        }
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
            binding.progress.visibility = View.INVISIBLE
            binding.exchange.visibility = View.VISIBLE
            binding.spinner1.visibility = View.VISIBLE
            binding.spinner2.visibility = View.VISIBLE
            binding.edit.visibility = View.VISIBLE
            binding.buy.visibility = View.VISIBLE
            binding.sell.visibility = View.VISIBLE
            binding.buyTitle.visibility = View.VISIBLE
            binding.sellTitle.visibility = View.VISIBLE
            binding.dollar.visibility = View.VISIBLE

            data = ArrayList()
            data.add(Currency("1", "UZS", null, "1", "1", ""))
            data.addAll(it as ArrayList<Currency>)
            spinnerAdapter1 = SpinnerAdapter(data)
            spinnerAdapter2 = SpinnerAdapter(data)
            binding.spinner1.adapter = spinnerAdapter1
            binding.spinner2.adapter = spinnerAdapter2
            if (position != -1) {
                binding.spinner1.setSelection(position + 1)
            }else binding.spinner1.setSelection(1)

            var nominal1_buy = 0.0
            var nominal1_cell = 0.0
            var nominal2_buy = 0.0
            var nominal2_cell = 0.0

            binding.edit.addTextChangedListener {
                if (binding.edit.text.toString().isNotEmpty()) {
                    val currency1 = data[binding.spinner1.selectedItemPosition]
                    val currency2 = data[binding.spinner2.selectedItemPosition]

                    if (currency1.nbu_buy_price!!.isEmpty()) {
                        nominal1_buy =
                            currency1.cb_price!!.toDouble()
                        nominal1_cell =
                            currency1.cb_price!!.toDouble()
                    } else {
                        nominal1_buy =
                            currency1.nbu_buy_price!!.toDouble()
                        nominal1_cell =
                            currency1.nbu_cell_price!!.toDouble()
                    }
                    if (currency2.nbu_buy_price!!.isEmpty()) {
                        nominal2_buy =
                            currency2.cb_price!!.toDouble()
                        nominal2_cell =
                            currency2.cb_price!!.toDouble()
                    } else {
                        nominal2_buy =
                            currency2.nbu_buy_price!!.toDouble()
                        nominal2_cell =
                            currency2.nbu_cell_price!!.toDouble()
                    }

                    binding.buy.text = ((binding.edit.text.toString()
                        .toDouble() * nominal1_buy) / nominal2_buy).toString()

                    binding.sell.text = ((binding.edit.text.toString()
                        .toDouble() * nominal1_cell) / nominal2_cell).toString()
                } else {
                    binding.buy.text = "0"
                    binding.sell.text = "0"
                }
            }

            binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (binding.edit.text.toString().isNotEmpty()) {
                        val currency1 = data[position]
                        val currency2 = data[binding.spinner2.selectedItemPosition]

                        if (currency1.nbu_buy_price!!.isEmpty()) {
                            nominal1_buy =
                                currency1.cb_price!!.toDouble()
                            nominal1_cell =
                                currency1.cb_price!!.toDouble()
                        } else {
                            nominal1_buy =
                                currency1.nbu_buy_price!!.toDouble()
                            nominal1_cell =
                                currency1.nbu_cell_price!!.toDouble()
                        }
                        if (currency2.nbu_buy_price!!.isEmpty()) {
                            nominal2_buy =
                                currency2.cb_price!!.toDouble()
                            nominal2_cell =
                                currency2.cb_price!!.toDouble()
                        } else {
                            nominal2_buy =
                                currency2.nbu_buy_price!!.toDouble()
                            nominal2_cell =
                                currency2.nbu_cell_price!!.toDouble()
                        }

                        binding.buy.text = ((binding.edit.text.toString()
                            .toDouble() * nominal1_buy) / nominal2_buy).toString()

                        binding.sell.text = ((binding.edit.text.toString()
                            .toDouble() * nominal1_cell) / nominal2_cell).toString()
                    } else {
                        binding.buy.text = "0"
                        binding.sell.text = "0"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (binding.edit.text.toString().isNotEmpty()) {
                        val currency1 = data[binding.spinner1.selectedItemPosition]
                        val currency2 = data[position]

                        if (currency1.nbu_buy_price!!.isEmpty()) {
                            nominal1_buy =
                                currency1.cb_price!!.toDouble()
                            nominal1_cell =
                                currency1.cb_price!!.toDouble()
                        } else {
                            nominal1_buy =
                                currency1.nbu_buy_price!!.toDouble()
                            nominal1_cell =
                                currency1.nbu_cell_price!!.toDouble()
                        }
                        if (currency2.nbu_buy_price!!.isEmpty()) {
                            nominal2_buy =
                                currency2.cb_price!!.toDouble()
                            nominal2_cell =
                                currency2.cb_price!!.toDouble()
                        } else {
                            nominal2_buy =
                                currency2.nbu_buy_price!!.toDouble()
                            nominal2_cell =
                                currency2.nbu_cell_price!!.toDouble()
                        }

                        binding.buy.text = ((binding.edit.text.toString()
                            .toDouble() * nominal1_buy) / nominal2_buy).toString()

                        binding.sell.text = ((binding.edit.text.toString()
                            .toDouble() * nominal1_cell) / nominal2_cell).toString()
                    } else {
                        binding.buy.text = "0"
                        binding.sell.text = "0"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            binding.exchange.setOnClickListener {
                val position1 = binding.spinner1.selectedItemPosition
                val position2 = binding.spinner2.selectedItemPosition

                binding.spinner1.setSelection(position2, true)
                binding.spinner2.setSelection(position1, true)

                if (binding.edit.text.toString().isNotEmpty()) {
                    val currency1 = data[position2]
                    val currency2 = data[position1]

                    if (currency1.nbu_buy_price!!.isEmpty()) {
                        nominal1_buy =
                            currency1.cb_price!!.toDouble()
                        nominal1_cell =
                            currency1.cb_price!!.toDouble()
                    } else {
                        nominal1_buy =
                            currency1.nbu_buy_price!!.toDouble()
                        nominal1_cell =
                            currency1.nbu_cell_price!!.toDouble()
                    }
                    if (currency2.nbu_buy_price!!.isEmpty()) {
                        nominal2_buy =
                            currency2.cb_price!!.toDouble()
                        nominal2_cell =
                            currency2.cb_price!!.toDouble()
                    } else {
                        nominal2_buy =
                            currency2.nbu_buy_price!!.toDouble()
                        nominal2_cell =
                            currency2.nbu_cell_price!!.toDouble()
                    }

                    binding.buy.text = ((binding.edit.text.toString()
                        .toDouble() * nominal1_buy) / nominal2_buy).toString()

                    binding.sell.text = ((binding.edit.text.toString()
                        .toDouble() * nominal1_cell) / nominal2_cell).toString()
                } else {
                    binding.buy.text = "0"
                    binding.sell.text = "0"
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