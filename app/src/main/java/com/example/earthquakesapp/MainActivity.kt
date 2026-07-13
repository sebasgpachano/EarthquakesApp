package com.example.earthquakesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.earthquakesapp.presentation.list.EarthquakeListRoute
import com.example.earthquakesapp.ui.theme.EarthquakesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EarthquakesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EarthquakeListRoute(
                        onQuakeClick = { id ->

                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HolaPreview() {
    EarthquakesAppTheme {
        Text(text = "Hola sismos")
    }
}
