package com.example.disampleproject.data.api

import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.util.Constants.API_KEY_3
import com.example.disampleproject.util.Constants.DEFAULT_MEAL_NUMBER
import com.example.disampleproject.util.Constants.GET_MEALS
import com.example.disampleproject.util.Constants.MAIN_COURSE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET(GET_MEALS)
    suspend fun fetchMeals(
        @Query("type") type: String = MAIN_COURSE,
        @Query("number") number: Int = DEFAULT_MEAL_NUMBER,
        @Query("apiKey") apiKey: String = API_KEY_3
    ): Response<MealResponse>
}