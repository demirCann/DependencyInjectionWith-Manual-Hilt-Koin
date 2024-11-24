package com.example.disampleproject.di.manual

import com.example.disampleproject.data.api.MealApi
import com.example.disampleproject.util.Constants.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ManualMealApiProvider {

    private val gson = GsonBuilder().create()

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val mealApi: MealApi = retrofit.create(MealApi::class.java)
}