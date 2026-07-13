package com.example.earthquakesapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakesapp.domain.model.Earthquake
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EarthquakeListUiState())
    val uiState: StateFlow<EarthquakeListUiState> = _uiState.asStateFlow()

    init {
        loadEarthquakes()
    }

    private fun loadEarthquakes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            delay(1000)
            val fakeData = listOf(
                Earthquake("1", 5.4, "Ciudad de México", System.currentTimeMillis(), 19.4, -99.1, 12.5, false, null, ""),
                Earthquake("2", 3.1, "Los Ángeles", System.currentTimeMillis() - 3_600_000, 34.0, -118.2, 8.0, false, null, "")
            )

            _uiState.update { it.copy(earthquakes = fakeData, isLoading = false) }
        }
    }
}