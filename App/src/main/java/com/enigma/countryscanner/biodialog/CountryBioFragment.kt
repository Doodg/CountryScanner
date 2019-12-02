package com.enigma.countryscanner.biodialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.enigma.presentation.model.CountryResponsePresentation
import kotlinx.android.synthetic.main.fragment_country_bio.*
import android.view.WindowManager
import com.enigma.countryscanner.R
import com.enigma.countryscanner.extension.loadImage


private const val ARG_COUNTRY = "country"

class CountryBioFragment : DialogFragment() {
    private var countryParam: CountryResponsePresentation? = null

    companion object {
        @JvmStatic
        fun createInstance(param1: CountryResponsePresentation) =
            CountryBioFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_COUNTRY, param1)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.countryBioCustomDialog)
        arguments?.let {
            countryParam = it.getSerializable(ARG_COUNTRY) as CountryResponsePresentation?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_bio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle(R.string.country_bio)
        countryParam?.alpha2Code?.let { imageViewCountryDialogFlag.loadImage(it) }
        textViewCountryName.text = resources.getString(R.string.country_name, countryParam?.name)
        textViewCountryCapital.text = resources.getString(R.string.capital_name, countryParam?.capital)
        textViewCountryPopulation.text = resources.getString(R.string.country_population, countryParam?.population)
        textViewCountryContinent.text = resources.getString(R.string.continent_name, countryParam?.region)
        textViewCountryContinentSubRegion.text = resources.getString(R.string.country_in_region, countryParam?.subregion)
        btnShareInfo.setOnClickListener {
            onItemShareClicked(countryParam)
        }


    }

    override fun onResume() {
        if (dialog != null && dialog!!.window != null) {
            val window = dialog!!.window
            window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        }
        super.onResume()
    }
    private fun onItemShareClicked(country: CountryResponsePresentation?) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        val title = resources.getString(R.string.find_out)
        val countryName =
            resources.getString(R.string.country_name_share, country?.name).plus(R.string.here_you_can_check_it)
        shareIntent.putExtra(
            Intent.EXTRA_TEXT, title.plus(countryName)
        )
        startActivity(Intent.createChooser(shareIntent, getString(R.string.app_name)))
    }
}
