package com.desafio.animeapi

import android.app.Application
import com.desafio.animeapi.di.networkModule
import com.desafio.animeapi.di.repositoryModule
import com.desafio.animeapi.di.useCaseModule
import com.desafio.animeapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AnimeApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AnimeApiApplication)
            modules(networkModule, repositoryModule, viewModelModule, useCaseModule)
        }
    }

}