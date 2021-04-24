package com.example.mapboxexample

import androidx.multidex.MultiDexApplication
import com.example.mapboxexample.di.viewModelModule
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
        sInstance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }
}