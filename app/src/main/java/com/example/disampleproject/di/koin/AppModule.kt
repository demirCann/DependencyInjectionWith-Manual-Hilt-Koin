package com.example.disampleproject.di.koin

import com.example.disampleproject.data.datasource.MealDataSource
import com.example.disampleproject.data.datasource.MealDataSourceWithKoin
import com.example.disampleproject.data.repository.KoinRepositoryImp
import com.example.disampleproject.data.repository.MealRepository
import com.example.disampleproject.ui.koin.KoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<MealDataSource> { MealDataSourceWithKoin(get()) }

    single<MealRepository> { KoinRepositoryImp(get()) }

    viewModel { KoinViewModel(get()) }
}