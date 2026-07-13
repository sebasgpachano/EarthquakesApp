package com.example.earthquakesapp.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.earthquakesapp.domain.model.Earthquake
import com.example.earthquakesapp.ui.theme.EarthquakesAppTheme

@Composable
fun EarthquakeListScreen(
    earthquakes: List<Earthquake>,
    onQuakeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = earthquakes,
            key = { earthquake -> earthquake.id }
        ) { earthquake ->
            EarthquakeCard(
                earthquake = earthquake,
                onClick = { onQuakeClick(earthquake.id) }
            )
        }
    }
}

@Composable
fun EarthquakeListRoute(
    onQuakeClick: (String) -> Unit,
    viewModel: EarthquakeListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EarthquakeListScreen(
        earthquakes = uiState.earthquakes,
        onQuakeClick = onQuakeClick,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun EarthquakeListScreenPreview() {
    val sampleQuakes = listOf(1.5, 3.2, 4.6, 5.5, 6.8).mapIndexed { index, mag ->
        Earthquake(
            id = index.toString(),
            magnitude = mag,
            place = "120 km SSW de Ciudad de México",
            time = System.currentTimeMillis() - (index + 1) * 3_600_000L,
            latitude = 19.4,
            longitude = -99.1,
            depth = 12.5,
            hasTsunami = false,
            alertLevel = null,
            detailUrl = ""
        )
    }
    EarthquakesAppTheme {
        EarthquakeListScreen(
            earthquakes = sampleQuakes,
            onQuakeClick = {}
        )
    }
}