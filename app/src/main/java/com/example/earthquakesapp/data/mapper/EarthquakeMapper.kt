package com.example.earthquakesapp.data.mapper

import com.example.earthquakesapp.data.remote.dto.FeatureDto
import com.example.earthquakesapp.domain.model.Earthquake

fun FeatureDto.toDomain(): Earthquake = Earthquake(
    id = id,
    magnitude = properties.mag ?: 0.0,
    place = properties.place ?: "Ubicación desconocida",
    time = properties.time,
    longitude = geometry.coordinates.getOrElse(0) { 0.0 },
    latitude = geometry.coordinates.getOrElse(1) { 0.0 },
    depth = geometry.coordinates.getOrElse(2) { 0.0 },
    hasTsunami = properties.tsunami == 1,
    alertLevel = properties.alert,
    detailUrl = properties.url
)