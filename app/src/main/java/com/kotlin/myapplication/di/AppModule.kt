package com.kotlin.myapplication.di

import com.kotlin.myapplication.BuildConfig
import com.kotlin.myapplication.api.ApiHelper
import com.kotlin.myapplication.api.ApiHelperImpl
import com.kotlin.myapplication.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by @erickrenata on 03/04/22.
 */

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(
            Interceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Cache-Control", "no-store")
                    .build()
                chain.proceed(newRequest)
            }
        )
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)