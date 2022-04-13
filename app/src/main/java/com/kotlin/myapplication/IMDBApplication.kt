package com.kotlin.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kotlin.myapplication.di.appModule
import com.kotlin.myapplication.di.newAppModule
import com.kotlin.myapplication.di.repoModule
import com.kotlin.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created by @erickrenata on 03/04/22.
 */

class IMDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@IMDBApplication)
            modules(listOf(appModule, repoModule, viewModelModule, newAppModule))
        }
    }
}