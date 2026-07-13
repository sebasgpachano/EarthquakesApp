package com.example.earthquakesapp.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.earthquakesapp.domain.model.Earthquake
import com.example.earthquakesapp.ui.theme.EarthquakesAppTheme

@Composable
fun EarthquakeCard(
    earthquake: Earthquake,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = String.format("%.1f", earthquake.magnitude),
                style = MaterialTheme.typography.titleLarge,
                color = magnitudeColor(earthquake.magnitude)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = earthquake.place,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Profundidad: ${earthquake.depth} km",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

fun magnitudeColor(magnitude: Double): Color = when {
    magnitude < 2.0 -> Color(0xFF4CAF50)   // verde — micro
    magnitude < 4.0 -> Color(0xFF8BC34A)   // verde claro — menor
    magnitude < 5.0 -> Color(0xFFFFC107)   // ámbar — ligero
    magnitude < 6.0 -> Color(0xFFFF9800)   // naranja — moderado
    else -> Color(0xFFF44336)              // rojo — fuerte
}

@Preview(showBackground = true)
@Composable
fun EarthquakeCardPreview() {
    EarthquakesAppTheme {
        Column {
            listOf(1.5, 3.2, 4.6, 5.5, 6.8).forEach { mag ->
                EarthquakeCard(
                    earthquake = Earthquake(
                        id = mag.toString(),
                        magnitude = mag,
                        place = "120 km SSW de Ciudad de México",
                        time = 1_700_000_000_000,
                        latitude = 19.4,
                        longitude = -99.1,
                        depth = 12.5,
                        hasTsunami = false,
                        alertLevel = "green",
                        detailUrl = ""
                    ),
                    onClick = {}
                )
            }
        }
    }
}