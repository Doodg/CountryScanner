package com.enigma.countryscanner

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.enigma.countryscanner.allcountries.AllCountriesFragment
import com.enigma.countryscanner.favoritecountries.FavoriteCountriesFragment

class HomeSectionsPagerAdapter(private val context: Context, fragment: FragmentManager) : FragmentStatePagerAdapter(
    fragment,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllCountriesFragment.newInstance()
            }
            1 -> {
                FavoriteCountriesFragment.newInstance()
            }
            else -> AllCountriesFragment.newInstance()

        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.resources.getString(R.string.countries)
            1 -> context.resources.getString(R.string.favourite_countries)
            else -> ""
        }
    }
}