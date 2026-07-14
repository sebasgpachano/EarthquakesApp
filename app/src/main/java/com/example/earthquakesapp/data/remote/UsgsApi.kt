package com.example.earthquakesapp.data.remote

import com.example.earthquakesapp.data.remote.dto.EarthquakeResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UsgsApi {
    @GET("summary/{feed}.geojson")
    suspend fun getEarthquakes(@Path("feed") feed: String): EarthquakeResponseDto
}