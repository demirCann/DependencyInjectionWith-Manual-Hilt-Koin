package com.example.disampleproject.data.repository

import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.util.ApiResult
import kotlinx.coroutines.flow.Flow

fun interface MealRepository {
    fun fetchMeals(mealType: String, numberOfMeals: Int): Flow<ApiResult<MealResponse>>
}