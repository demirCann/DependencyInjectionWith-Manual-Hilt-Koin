package com.example.disampleproject.ui.manual

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.data.repository.MealRepository
import com.example.disampleproject.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ManualViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _mealState = MutableStateFlow<MealResponse?>(null)
    val mealState = _mealState.asStateFlow()

    fun fetchMeals(mealType: String, numberOfMeals: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.fetchMeals(mealType, numberOfMeals).collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        _mealState.value = apiResult.data
                    }

                    is ApiResult.Error -> {
                        // Handle error
                    }

                    ApiResult.Loading -> {}
                }
            }
        }
    }
}