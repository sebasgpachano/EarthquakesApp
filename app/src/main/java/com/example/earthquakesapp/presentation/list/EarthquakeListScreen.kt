package com.example.earthquakesapp.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.earthquakesapp.domain.model.Earthquake
import com.example.earthquakesapp.ui.theme.EarthquakesAppTheme

@Composable
fun EarthquakeListScreen(
    uiState: EarthquakeListUiState,
    onQuakeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.error != null -> {
                Text(
                    text = uiState.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = uiState.earthquakes,
                        key = { earthquake -> earthquake.id }
                    ) { earthquake ->
                        EarthquakeCard(
                            earthquake = earthquake,
                            onClick = { onQuakeClick(earthquake.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EarthquakeListRoute(
    onQuakeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EarthquakeListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EarthquakeListScreen(
        uiState = uiState,
        onQuakeClick = onQuakeClick,
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Cargando")
@Composable
fun ListLoadingPreview() {
    EarthquakesAppTheme {
        EarthquakeListScreen(
            uiState = EarthquakeListUiState(isLoading = true),
            onQuakeClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Error")
@Composable
fun ListErrorPreview() {
    EarthquakesAppTheme {
        EarthquakeListScreen(
            uiState = EarthquakeListUiState(error = "No se pudo cargar. Revisa tu conexión."),
            onQuakeClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Con datos")
@Composable
fun ListContentPreview() {
    val sampleQuakes = listOf(2.1, 4.5, 6.2).mapIndexed { i, mag ->
        Earthquake(i.toString(), mag, "Zona sísmica de ejemplo", System.currentTimeMillis(), 0.0, 0.0, 10.0, false, null, "")
    }
    EarthquakesAppTheme {
        EarthquakeListScreen(
            uiState = EarthquakeListUiState(earthquakes = sampleQuakes),
            onQuakeClick = {}
        )
    }
}