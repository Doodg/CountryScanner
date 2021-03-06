package com.enigma.countryscanner

import android.app.SearchManager
import android.content.Context
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.enigma.countryscanner.allcountries.AllCountriesFragment
import kotlinx.android.synthetic.main.activity_main.*


class TabsMainActivity : AppCompatActivity() {
    private var searchView: SearchView? = null

    lateinit var mSectionsPagerAdapter: HomeSectionsPagerAdapter
    val fragmentAllCountries: AllCountriesFragment by lazy {
        mSectionsPagerAdapter.getItem(0) as AllCountriesFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = HomeSectionsPagerAdapter(this, supportFragmentManager)
        viewPagerContainer.adapter = mSectionsPagerAdapter
        tabsLayout.addTab(tabsLayout.newTab().setText(R.string.countries))
        tabsLayout.addTab(tabsLayout.newTab().setText(R.string.favourite_countries))
        tabsLayout.tabGravity = TabLayout.GRAVITY_FILL
        viewPagerContainer.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabsLayout
            )
        )
        tabsLayout.setupWithViewPager(viewPagerContainer)
        tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPagerContainer.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                invalidateOptionsMenu()
            }
        })
    }

    fun hideSoftKeyBoard() {
        if (currentFocus != null)
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow((currentFocus).windowToken, 0)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (viewPagerContainer.currentItem != 0) {
            menu?.findItem(R.id.action_search)?.isVisible = false
            hideSoftKeyBoard()
        } else {
            menu?.findItem(R.id.action_search)?.isVisible = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideSoftKeyBoard()
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { fragmentAllCountries.searchQuery(it) }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!searchView?.isIconified()!!) {
            searchView?.setIconified(true);
            return
        }
        super.onBackPressed()
    }
}
