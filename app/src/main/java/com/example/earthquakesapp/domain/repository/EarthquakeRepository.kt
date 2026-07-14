package com.example.earthquakesapp.domain.repository

import com.example.earthquakesapp.domain.model.Earthquake

interface EarthquakeRepository {
    suspend fun getEarthquakes(feed: String): List<Earthquake>
}