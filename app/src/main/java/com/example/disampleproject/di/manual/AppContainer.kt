package com.example.disampleproject.di.manual

import androidx.lifecycle.ViewModelProvider
import com.example.disampleproject.data.datasource.MealDataSource
import com.example.disampleproject.data.datasource.MealDataSourceWithManualDI
import com.example.disampleproject.data.repository.ManualRepositoryImp
import com.example.disampleproject.data.repository.MealRepository
import com.example.disampleproject.ui.manual.ManualViewModel
import com.example.disampleproject.util.ViewModelFactory

class AppContainer {

    private val remoteDataSource: MealDataSource by lazy {
        MealDataSourceWithManualDI(ManualMealApiProvider.mealApi)
    }

    private val mealRepository: MealRepository by lazy {
        ManualRepositoryImp(remoteDataSource)
    }

    val manualViewModelFactory: ViewModelProvider.Factory by lazy {
        ViewModelFactory {
            ManualViewModel(mealRepository)
        }
    }

}