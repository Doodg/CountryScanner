package com.enigma.countryscanner.favoritecountries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigma.countryscanner.extension.loadImage
import com.enigma.presentation.model.CountryResponsePresentation
import kotlinx.android.synthetic.main.countries_list_item.view.imageViewCountryFlag
import kotlinx.android.synthetic.main.favourite_country_list_item.view.*
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import com.enigma.countryscanner.R


class FavouriteCountryAdapter(private val  onReadMoreClicked:(country:CountryResponsePresentation)->Unit) : RecyclerView.Adapter<FavouriteCountryAdapter.MyViewHolder>() {
    var cachedItems = ArrayList<CountryResponsePresentation>()
    private var mLastClickTime: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favourite_country_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cachedItems.size
    }

    override fun onBindViewHolder(holder: FavouriteCountryAdapter.MyViewHolder, position: Int) {
        val country = cachedItems.get(position)
        holder.itemView.imageViewCountryFlag.loadImage(country.alpha2Code)
        holder.itemView.textViewFavName.text = country.name
        holder.itemView.textViewCurrencies.text = holder.itemView.context.resources.getString(R.string.country_currency,country.currencies.toString())
        holder.itemView.textViewCallingCode.text = holder.itemView.context.resources.getString(R.string.country_calling_code,country.callingCodes.toString())
        holder.itemView.textViewTimeZone.text=country.timezones?.first()
        holder.itemView.textViewReadMore.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) return@setOnClickListener;
            onReadMoreClicked(country)
            mLastClickTime = SystemClock.elapsedRealtime()

        }
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}