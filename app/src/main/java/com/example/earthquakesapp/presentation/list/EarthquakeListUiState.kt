package com.example.earthquakesapp.presentation.list

import com.example.earthquakesapp.domain.model.Earthquake

data class EarthquakeListUiState(
    val earthquakes: List<Earthquake> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)