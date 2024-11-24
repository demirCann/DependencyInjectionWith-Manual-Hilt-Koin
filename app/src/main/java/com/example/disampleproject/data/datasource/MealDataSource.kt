package com.example.disampleproject.data.datasource

import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.util.ApiResult
import kotlinx.coroutines.flow.Flow

fun interface MealDataSource {
    fun fetchMeals(mealType: String, numberOfMeals: Int): Flow<ApiResult<MealResponse>>
}