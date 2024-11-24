package com.example.disampleproject.data.datasource

import com.example.disampleproject.data.api.MealApi
import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.util.ApiResult
import com.example.disampleproject.util.apiFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealDataSourceWithHilt @Inject constructor(
    private val mealApi: MealApi
) : MealDataSource {
    override fun fetchMeals(mealType: String, numberOfMeals: Int): Flow<ApiResult<MealResponse>> = apiFlow {
        mealApi.fetchMeals(mealType, numberOfMeals)
    }
}