package com.example.earthquakesapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakesapp.domain.model.Earthquake
import com.example.earthquakesapp.domain.repository.EarthquakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeListViewModel @Inject constructor(
    private val repository: EarthquakeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EarthquakeListUiState())
    val uiState: StateFlow<EarthquakeListUiState> = _uiState.asStateFlow()

    init {
        loadEarthquakes()
    }

    private fun loadEarthquakes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val earthquakes = repository.getEarthquakes("all_day")
                _uiState.update {
                    it.copy(earthquakes = earthquakes, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "No se pudieron cargar los sismos", isLoading = false)
                }
            }
        }
    }
}