package uz.pdp.exchangerates.ui.currency

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.pdp.exchangerates.R
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.FragmentCurrencyBinding
import uz.pdp.exchangerates.ui.currency.adapter.CurrencyAdapter
import uz.pdp.exchangerates.viewmodel.CurrencyViewModel


class CurrencyFragment : Fragment() {

    private lateinit var currencyViewModel: CurrencyViewModel
    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!
    lateinit var data: ArrayList<Currency>
    lateinit var adapter: CurrencyAdapter
    lateinit var onDataPass: OnDataPass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Pro Valyuta kurslari"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currencyViewModel =
            ViewModelProvider(this).get(CurrencyViewModel::class.java)
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        val root: View = binding.root


        loadData()


        return root
    }

    private fun loadData() {
        data = ArrayList()
        currencyViewModel.getCurrency().observe(viewLifecycleOwner, {
            adapter = CurrencyAdapter()
            data.addAll(it)
            adapter.setAdapter(it as ArrayList, object : CurrencyAdapter.OnItemClick {
                override fun onCLick(currency: Currency) {
                    onDataPass.onDataPass("calculator")
                    Toast.makeText(binding.root.context, currency.code, Toast.LENGTH_SHORT).show()
                    Log.d("AAAA", "onCLick: ${currency.code}")
                }
            })
            binding.rv.adapter = adapter
            binding.progress.visibility = View.INVISIBLE
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val myActionMenuItem = menu.findItem(R.id.search)
        val searchView = myActionMenuItem.actionView as SearchView
        val txtSearch =
            searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.hint = "Search currency"
        txtSearch.setHintTextColor(Color.LTGRAY)
        txtSearch.setBackgroundResource(android.R.color.transparent)
        txtSearch.setTextColor(Color.BLACK)
        txtSearch.addTextChangedListener {
            filter(it.toString())
        }
    }

    private fun filter(query: String) {
        val temp: MutableList<Currency> = ArrayList()
        for (d in data) {
            if (d.code?.lowercase()!!.contains(query.lowercase())) {
                temp.add(d)
            }
        }
        adapter.updateList(temp)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnDataPass {
        fun onDataPass(data: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onDataPass = context as OnDataPass
    }
}