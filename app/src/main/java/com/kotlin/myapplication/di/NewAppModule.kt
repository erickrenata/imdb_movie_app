package com.kotlin.myapplication.di

import com.kotlin.myapplication.BuildConfig
import com.kotlin.myapplication.api.ApiHelper
import com.kotlin.myapplication.api.ApiHelperImpl
import com.kotlin.myapplication.api.ApiService
import com.kotlin.myapplication.apinew.NewApiHelper
import com.kotlin.myapplication.apinew.NewApiHelperImpl
import com.kotlin.myapplication.apinew.NewApiService
import com.kotlin.myapplication.db.MovieDatabase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by @erickrenata on 13/04/22.
 */

val newAppModule = module {
//    single { provideOkHttpClients() }
//    single { provideNewRetrofit() }
    single { provideNewApiService(get()) }

    single(qualifier = named("login_retrofit")) { provideNewRetrofit() }
//    single("login_retrofit"){
//        provideNewRetrofit()
//    }

    single<NewApiHelper> {
        return@single NewApiHelperImpl(get())
    }
}
//
//private fun provideOkHttpClients() = if (BuildConfig.DEBUG) {
//    val loggingInterceptor = HttpLoggingInterceptor()
//    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .addNetworkInterceptor(
//            Interceptor { chain ->
//                val request = chain.request()
//                val newRequest = request.newBuilder()
//                    .addHeader("Accept", "application/json")
//                    .addHeader("Content-Type", "application/json")
//                    .addHeader("Cache-Control", "no-cache")
//                    .addHeader("Cache-Control", "no-store")
//                    .build()
//                chain.proceed(newRequest)
//            }
//        )
//        .build()
//} else OkHttpClient
//    .Builder()
//    .build()

private fun provideNewRetrofit(
//    okHttpClient: OkHttpClient
): Retrofit {
    val okHttpClient = OkHttpClient
        .Builder()
        .build()
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://6124ba6d300c540017873d1e.mockapi.io/api/v1/")
        .client(okHttpClient)
        .build()
}

private fun provideNewApiService(retrofit: Retrofit): NewApiService =
    retrofit.create(NewApiService::class.java)