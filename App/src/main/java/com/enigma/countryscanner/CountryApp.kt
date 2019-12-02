package com.enigma.countryscanner

import android.app.Application
import com.enigma.cache.di.cacheModule
import com.enigma.datadistrbuter.di.dataModule
import com.enigma.presentation.di.presentationModule
import com.enigma.remote.di.remoteModule
import com.enigma.usecase.di.useCaseModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CountryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        setupStetcho()
        setupKoinModules()
    }

    private fun setupKoinModules() {
        startKoin {
            androidContext(this@CountryApp)
            modules(
                listOf(
                    presentationModule,
                    dataModule,
                    remoteModule,
                    useCaseModule,
                    cacheModule
                )
            )
        }
    }

    private fun setupStetcho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }

    companion object {
        private var INSTANCE: CountryApp? = null
        fun getAppInstance(): CountryApp {
            if (INSTANCE == null)
                throw IllegalStateException("no application attached!")
            return INSTANCE as CountryApp
        }

    }
}