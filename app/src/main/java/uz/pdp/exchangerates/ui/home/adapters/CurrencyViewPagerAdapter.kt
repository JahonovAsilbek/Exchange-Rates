package uz.pdp.exchangerates.ui.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import uz.pdp.exchangerates.database.entities.Currency
import uz.pdp.exchangerates.ui.home.ViewPagerFragment

class CurrencyViewPagerAdapter(var data: ArrayList<Currency>, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Fragment {
        return ViewPagerFragment.newInstance(data[position])
    }

}