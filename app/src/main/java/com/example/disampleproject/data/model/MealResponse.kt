package com.example.disampleproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    val number: Int,
    val offset: Int,
    val results: List<Meal>,
    val totalResults: Int
)
