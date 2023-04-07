package com.desafio.animeapi.di

import com.desafio.animeapi.BuildConfig
import com.desafio.animeapi.data.repository.RepositoryImpl
import com.desafio.animeapi.domain.repository.Repository
import com.desafio.animeapi.domain.usecase.do_register.DoRegisterUseCase
import com.desafio.animeapi.domain.usecase.get_animes.GetAnimesUseCase
import com.desafio.animeapi.domain.usecase.get_login.DoLoginUseCase
import com.desafio.animeapi.presentation.anime_list.AnimeListViewModel
import com.desafio.animeapi.presentation.login.LoginViewModel
import com.desafio.animeapi.presentation.register.RegisterViewModel
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

val repositoryModule = module {
    single<Repository> { RepositoryImpl(api = get()) }
}

val useCaseModule = module {
    factory { GetAnimesUseCase(repository = get()) }
    factory { DoLoginUseCase(auth = get()) }
    factory { DoRegisterUseCase(auth = get()) }
}

val viewModelModule = module {
    viewModel { AnimeListViewModel(getAnimeUseCase = get()) }
    viewModel { LoginViewModel(doLoginUseCase = get()) }
    viewModel { RegisterViewModel(doRegisterUseCase = get()) }
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