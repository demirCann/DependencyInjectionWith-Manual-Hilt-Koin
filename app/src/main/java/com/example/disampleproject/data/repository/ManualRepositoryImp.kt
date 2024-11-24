package com.example.disampleproject.data.repository

import com.example.disampleproject.data.datasource.MealDataSource
import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.util.ApiResult
import kotlinx.coroutines.flow.Flow

class ManualRepositoryImp(
    private val remoteMealDataSource: MealDataSource
) : MealRepository {

    override fun fetchMeals(mealType: String, numberOfMeals: Int): Flow<ApiResult<MealResponse>> {
        return remoteMealDataSource.fetchMeals(mealType, numberOfMeals)
    }
}