package com.enigma.countryscanner.favoritecountries


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.enigma.countryscanner.R
import com.enigma.presentation.Resource
import com.enigma.presentation.model.CountryResponsePresentation
import com.enigma.presentation.viewmodel.FavouriteCountryViewModel
import kotlinx.android.synthetic.main.fragment_favorite_countries.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.enigma.countryscanner.extension.hide
import com.enigma.countryscanner.extension.show
import java.net.URLEncoder


class FavoriteCountriesFragment : Fragment() {
    private val favouriteCountryViewModel: FavouriteCountryViewModel by viewModel()
    private lateinit var favouriteCountryAdapter: FavouriteCountryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteCountryAdapter = FavouriteCountryAdapter(::onReadMoreClicked)
        initView()
        fetchData()
        observeOnFavouriteCountries()

    }

    private fun observeOnFavouriteCountries() {
        favouriteCountryViewModel.observeOnGetFavouritCountries(this, Observer {
            when (it.state) {
                Resource.State.SUCCESS -> {
                    if (it.values.isNullOrEmpty()) {
                        recyclerViewFavouriteCountries.hide()
                        textViewNOFavouriteAddYet.show()
                        imageViewNoFavouriteIcon.show()
                    } else {
                        favouriteCountryAdapter.cachedItems =
                            it.values as ArrayList<CountryResponsePresentation>
                        favouriteCountryAdapter.notifyDataSetChanged()
                        recyclerViewFavouriteCountries.show()
                        textViewNOFavouriteAddYet.hide()
                        imageViewNoFavouriteIcon.hide()

                    }
                }
                Resource.State.LOADING -> {
                }
                Resource.State.ERROR -> {
                    Toast.makeText(context, R.string.error_occurred, Toast.LENGTH_SHORT).show()

                }

            }
        })
    }

    private fun fetchData() {
        favouriteCountryViewModel.retrieveFavouriteCountries()
    }

    private fun initView() {
        recyclerViewFavouriteCountries.apply {
            adapter = favouriteCountryAdapter
            layoutManager = GridLayoutManager(context, 2)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun onReadMoreClicked(countryPresentation: CountryResponsePresentation) {
        val escapedQuery = URLEncoder.encode(countryPresentation.name, "UTF-8")
        val uri = Uri.parse("http://www.google.com/#q=$escapedQuery")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = FavoriteCountriesFragment()
    }
}
