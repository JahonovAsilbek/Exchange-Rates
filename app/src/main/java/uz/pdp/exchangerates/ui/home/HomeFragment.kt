package uz.pdp.exchangerates.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tab_item.view.*
import uz.pdp.exchangerates.R
import uz.pdp.exchangerates.database.AppDatabase
import uz.pdp.exchangerates.database.daos.CurrenyDao
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.databinding.FragmentHomeBinding
import uz.pdp.exchangerates.ui.home.adapters.CurrencyRvAdapter
import uz.pdp.exchangerates.ui.home.adapters.CurrencyViewPagerAdapter
import uz.pdp.exchangerates.viewmodel.CurrencyViewModel

class HomeFragment : Fragment() {

    private lateinit var currencyViewModel: CurrencyViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var getDao: CurrenyDao
    lateinit var viewPagerAdapter: CurrencyViewPagerAdapter
    lateinit var currencyRvAdapter: CurrencyRvAdapter
    lateinit var titleList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Pro Valyuta kurslari"
        getDao = AppDatabase.get.getDatabase().getDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currencyViewModel =
            ViewModelProvider(this).get(CurrencyViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadData()




        return binding.root
    }

    private fun loadData() {
        titleList = ArrayList()
        currencyRvAdapter = CurrencyRvAdapter()

        currencyViewModel.getCurrency().observe(viewLifecycleOwner, {
            binding.progress.visibility = View.INVISIBLE
            binding.text.visibility = View.VISIBLE
            for (currency in it) {
                if (getDao.getCurrencyByDate(currency.date!!, currency.code!!).isEmpty()) {
                    getDao.insertCurency(currency)
                }
                titleList.add(currency.code!!)
            }
            viewPagerAdapter = CurrencyViewPagerAdapter(it as ArrayList, childFragmentManager)
            binding.vpCard.adapter = viewPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.vpCard)
            binding.tabLayout2.setupWithViewPager(binding.vpCard)
            setTabs()

            binding.vpCard.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    rvAdapter(it[position])
                }

                override fun onPageSelected(position: Int) {}
                override fun onPageScrollStateChanged(state: Int) {}
            })
        })
    }

    private fun rvAdapter(currency: Currency) {
        val data = getDao.getCurrencyByDateRv(
            currency.date!!,
            currency.code!!
        ).reversed()
        currencyRvAdapter.setAdapter(data)
        currencyRvAdapter.notifyDataSetChanged()
        binding.rv.adapter = currencyRvAdapter
    }

    private fun setTabs() {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tabBind =
                LayoutInflater.from(binding.root.context).inflate(R.layout.tab_item, null, false)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = tabBind

            tabBind.title.text = titleList[i]

            if (i == 0) {
                tabBind.title.setBackgroundResource(R.drawable.tab_back_selected)
                tabBind.title.setTextColor(resources.getColor(R.color.white))
            } else {
                tabBind.title.setBackgroundResource(R.drawable.tab_back_unselected)
                tabBind.title.setTextColor(resources.getColor(R.color.tab_color))
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val view = tab?.customView
                view?.title?.setBackgroundResource(R.drawable.tab_back_selected)
                view?.title?.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val view = tab?.customView
                view?.title?.setBackgroundResource(R.drawable.tab_back_unselected)
                view?.title?.setTextColor(resources.getColor(R.color.tab_color))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}