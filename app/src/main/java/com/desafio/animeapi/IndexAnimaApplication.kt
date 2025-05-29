package com.desafio.animeapi

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.desafio.animeapi.di.animeRepositoryModule
import com.desafio.animeapi.di.networkModule
import com.desafio.animeapi.di.useCaseModule
import com.desafio.animeapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class IndexAnimaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(
                listOf(
                    networkModule,
                    animeRepositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}