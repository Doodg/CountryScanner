package com.enigma.countryscanner.allcountries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigma.countryscanner.biodialog.CountryBioFragment
import com.enigma.countryscanner.R
import com.enigma.presentation.Resource
import com.enigma.presentation.model.CountryResponsePresentation
import com.enigma.presentation.viewmodel.CountriesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_all_countries.*
import org.koin.android.ext.android.inject

class AllCountriesFragment : Fragment() {
    private val countriesViewModel: CountriesViewModel by inject()
    private lateinit var allCountriesAdapter: AllCountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allCountriesAdapter = AllCountriesAdapter(::onCountryItemSelect, ::onItemFavouriteClicked)
        initViews()
        requestExecute()
        initObservers()
    }

    private fun initObservers() {
        countriesViewModel.observeOnGetAllCountries(this, Observer { resource ->
            when (resource?.state) {
                Resource.State.LOADING -> {
                    swipeToRefresh.isRefreshing = true
                }
                Resource.State.SUCCESS -> {
                    swipeToRefresh.isRefreshing = false
                    swipeToRefresh.isEnabled = false
                    resource.values?.let { allCountriesAdapter.items.addAll(it) }
                    allCountriesAdapter.notifyDataSetChanged()

                }
                Resource.State.ERROR -> {
                    Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
                    swipeToRefresh.isRefreshing = false

                }
            }
        })
    }


    private fun requestExecute() {
        countriesViewModel.executeGetAllCountriesUseCase()
    }

    private fun initViews() {
        swipeToRefresh.setOnRefreshListener {
            requestExecute()
        }
        recyclerViewAllCountries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allCountriesAdapter
        }
    }

    private fun onCountryItemSelect(position: Int, country: CountryResponsePresentation) {
        val dialogFragment = CountryBioFragment.createInstance(country)
        dialogFragment.show(activity!!.supportFragmentManager, "fdf")
    }

    private fun onItemFavouriteClicked(
        position: Int,
        country: CountryResponsePresentation,
        isFavourite: Boolean
    ) {
        country.isFavourite = isFavourite
        countriesViewModel.markAsFavourite(country)
        allCountriesAdapter.notifyItemChanged(position, country)
    }


    companion object {
        fun newInstance() = AllCountriesFragment()

    }
}
