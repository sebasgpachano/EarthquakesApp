package com.example.earthquakesapp.domain.model

data class Earthquake(
    val id: String,
    val magnitude: Double,
    val place: String,
    val time: Long,
    val latitude: Double,
    val longitude: Double,
    val depth: Double,
    val hasTsunami: Boolean,
    val alertLevel: String?,
    val detailUrl: String
)