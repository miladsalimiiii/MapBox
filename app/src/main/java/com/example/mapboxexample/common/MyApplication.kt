package com.example.mapboxexample.common

import androidx.multidex.MultiDexApplication
import com.example.mapboxexample.R
import com.example.mapboxexample.di.apiModule
import com.example.mapboxexample.di.repositoryModule
import com.example.mapboxexample.di.utilModule
import com.example.mapboxexample.di.viewModelModule
import com.mapbox.mapboxsdk.Mapbox
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : MultiDexApplication(), KoinComponent {

    companion object {
        private var sInstance = MyApplication()
        fun getInstance(): MyApplication {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        sInstance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    apiModule,
                    utilModule,
                    repositoryModule
                )
            )
        }
    }
}