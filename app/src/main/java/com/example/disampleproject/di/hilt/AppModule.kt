package com.example.disampleproject.di.hilt

import com.example.disampleproject.data.datasource.MealDataSource
import com.example.disampleproject.data.repository.HiltRepositoryImp
import com.example.disampleproject.data.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMealRepository(
        mealDataSource: MealDataSource
    ): MealRepository {
        return HiltRepositoryImp(mealDataSource)
    }

}