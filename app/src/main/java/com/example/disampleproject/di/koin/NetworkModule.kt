package com.example.disampleproject.di.koin

import com.example.disampleproject.data.api.MealApi
import com.example.disampleproject.util.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Gson> {
        GsonBuilder().create()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single {
        get<Retrofit>().create(MealApi::class.java)
    }
}