package com.example.earthquakesapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponseDto(
    val features: List<FeatureDto>
)

@Serializable
data class FeatureDto(
    val id: String,
    val properties: PropertiesDto,
    val geometry: GeometryDto
)

@Serializable
data class PropertiesDto(
    val mag: Double? = null,
    val place: String? = null,
    val time: Long,
    val tsunami: Int = 0,
    val alert: String? = null,
    val url: String
)

@Serializable
data class GeometryDto(
    val coordinates: List<Double>
)