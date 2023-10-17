package com.desafio.animeapi.di

import com.desafio.animeapi.BuildConfig
import com.desafio.animeapi.data.repository.AnimeRepositoryImpl
import com.desafio.animeapi.domain.repository.AnimeRepository
import com.desafio.animeapi.domain.usecase.do_initialize.DoInitializeApplication
import com.desafio.animeapi.domain.usecase.do_register.DoRegisterUseCase
import com.desafio.animeapi.domain.usecase.get_animelist.GetTopAnimeListUseCase
import com.desafio.animeapi.domain.usecase.get_animes.GetAnimesUseCase
import com.desafio.animeapi.domain.usecase.get_login.DoLoginUseCase
import com.desafio.animeapi.presentation.anime_list.AnimeListViewModel
import com.desafio.animeapi.presentation.home.HomeViewModel
import com.desafio.animeapi.presentation.login.LoginViewModel
import com.desafio.animeapi.presentation.register.RegisterViewModel
import com.desafio.animeapi.presentation.splash_screen.SplashScreenViewModel
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { getRetrofit(get()) }
    factory { getOkHttpClient() }
    factory { getApi(get()) }
    factory { getFirebaseAuth() }
}

val animeRepositoryModule = module {
    single<AnimeRepository> { AnimeRepositoryImpl(api = get()) }
}

val useCaseModule = module {
    factory { GetAnimesUseCase(animeRepository = get()) }
    factory { DoLoginUseCase(auth = get()) }
    factory { DoRegisterUseCase(auth = get()) }
    factory { DoInitializeApplication() }
    factory { GetTopAnimeListUseCase(animeRepository = get()) }
}

val viewModelModule = module {
    viewModel { AnimeListViewModel(getAnimeUseCase = get()) }
    viewModel { LoginViewModel(doLoginUseCase = get()) }
    viewModel { RegisterViewModel(doRegisterUseCase = get()) }
    viewModel { SplashScreenViewModel(doInitializeApplication = get()) }
    viewModel { HomeViewModel(getTopAnimeListUseCase = get()) }
}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

private fun getOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = Level.BODY
    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}

private fun getApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

private fun getFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()