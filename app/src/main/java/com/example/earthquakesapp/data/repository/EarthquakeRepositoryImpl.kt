package com.example.earthquakesapp.data.repository

import com.example.earthquakesapp.data.mapper.toDomain
import com.example.earthquakesapp.data.remote.UsgsApi
import com.example.earthquakesapp.domain.model.Earthquake
import com.example.earthquakesapp.domain.repository.EarthquakeRepository
import javax.inject.Inject

class EarthquakeRepositoryImpl @Inject constructor(
    private val api: UsgsApi
) : EarthquakeRepository {

    override suspend fun getEarthquakes(feed: String): List<Earthquake> {
        val response = api.getEarthquakes(feed)
        return response.features.map { it.toDomain() }
    }
}