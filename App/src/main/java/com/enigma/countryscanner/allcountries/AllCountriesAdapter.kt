package com.enigma.countryscanner.allcountries

import android.os.SystemClock
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.enigma.presentation.model.CountryResponsePresentation
import kotlinx.android.synthetic.main.countries_list_item.view.*
import com.enigma.countryscanner.R
import com.enigma.countryscanner.extension.loadImage
import androidx.appcompat.widget.PopupMenu
import android.widget.Filter
import android.widget.Filterable


class AllCountriesAdapter(
    private val onItemCountryClicked: (position: Int, country: CountryResponsePresentation) -> Unit,
    private val onItemFavoriteItemClicked: (position: Int, country: CountryResponsePresentation, isFavourite: Boolean) -> Unit
) : RecyclerView.Adapter<AllCountriesAdapter.ViewHolder>(), Filterable {
    private var countryItems = ArrayList<CountryResponsePresentation>()
    var unFilterCountries = ArrayList<CountryResponsePresentation>()
    private var mLastClickTime: Long = 0
    private var filteredCountries: List<CountryResponsePresentation>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.countries_list_item, parent, false)
        return ViewHolder(view)
    }

    fun setAllCountries(items :ArrayList<CountryResponsePresentation>){
        countryItems.addAll(items)
        unFilterCountries.addAll(items)
    }
    override fun getItemCount(): Int {
        return countryItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!countryItems.isNullOrEmpty()) {
            holder.bindItem(countryItems.get(position))
            holder.itemView.setOnClickListener {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) return@setOnClickListener;

                onItemCountryClicked(position, countryItems.get(position))
                mLastClickTime = SystemClock.elapsedRealtime()
            }
            holder.itemView.imageViewIconStar.setOnClickListener {
                onItemFavoriteItemClicked(
                    position,
                    countryItems.get(position),
                    !countryItems.get(position).isFavourite
                )
            }
            if (!countryItems.get(position).isFavourite) {
                holder.itemView.imageViewIconStar.setImageResource(R.drawable.icon_favourite_outline_ff_7900)
            } else {
                holder.itemView.imageViewIconStar.setImageResource(R.drawable.icon_favourite_filled_ff_7900)

            }
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(country: CountryResponsePresentation) {
            itemView.textViewCountryName.text = country.name
            country.alpha2Code.let {
                itemView.imageViewCountryFlag.loadImage(it)
            }

        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override
    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty() || charString.length < 2) {
                    filteredCountries = unFilterCountries
                } else {
                    val filteredList = ArrayList<CountryResponsePresentation>()
                    for (row in countryItems) {
                        if (row.name?.toLowerCase()?.contains(charString.toLowerCase())!! || row.nativeName?.contains(
                                charSequence
                            )!!
                        ) {
                            filteredList.add(row)
                        }
                    }

                    filteredCountries = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredCountries
                return filterResults
            }

            override
            fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredCountries = filterResults.values as ArrayList<CountryResponsePresentation>
                countryItems.clear()
                countryItems.addAll(filteredCountries as ArrayList<CountryResponsePresentation>)
                notifyDataSetChanged()
            }
        }
    }

}