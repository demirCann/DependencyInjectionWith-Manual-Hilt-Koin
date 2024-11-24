package com.example.disampleproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
)
