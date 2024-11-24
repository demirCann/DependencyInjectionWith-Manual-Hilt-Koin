package com.example.disampleproject.ui.hilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disampleproject.data.model.MealResponse
import com.example.disampleproject.data.repository.MealRepository
import com.example.disampleproject.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _mealState = MutableStateFlow<MealResponse?>(null)
    val mealState = _mealState.asStateFlow()

    fun fetchMeals(mealType: String, numberOfMeals: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMeals(mealType, numberOfMeals).collect { apiResult ->
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