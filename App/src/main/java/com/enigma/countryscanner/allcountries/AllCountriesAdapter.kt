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
    var items = ArrayList<CountryResponsePresentation>()
    private var mLastClickTime: Long = 0
    private var countryFiltered: List<CountryResponsePresentation>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.countries_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!items.isNullOrEmpty()) {
            holder.bindItem(items.get(position))
            holder.itemView.setOnClickListener {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) return@setOnClickListener;

                onItemCountryClicked(position, items.get(position))
                mLastClickTime = SystemClock.elapsedRealtime()
            }
            holder.itemView.imageViewIconStar.setOnClickListener {
                onItemFavoriteItemClicked(
                    position,
                    items.get(position),
                    !items.get(position).isFavourite
                )
            }
            if (!items.get(position).isFavourite) {
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
                if (charString.isEmpty()) {
                    countryFiltered = items
                } else {
                    var filteredList = ArrayList<CountryResponsePresentation>()
                    for (row in items) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name?.toLowerCase()?.contains(charString.toLowerCase())!! || row.nativeName?.contains(charSequence)!!) {
                            filteredList.add(row)
                        }
                    }

                    countryFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = countryFiltered
                return filterResults
            }

            override
            fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                countryFiltered = filterResults.values as ArrayList<CountryResponsePresentation>

                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }

}