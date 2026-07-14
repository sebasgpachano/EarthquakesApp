# Earthquakes

An Android app that displays real-time earthquake data from the [USGS](https://earthquake.usgs.gov/) feeds. Built entirely with Jetpack Compose and a clean, layered architecture.

> **Status:** work in progress. The core feature (browsing recent earthquakes) is fully functional. See the [Roadmap](#roadmap) for what's planned next.

## Screenshots

<!-- TODO: add screenshots here once captured.
Example:
| List | Loading | Error |
|------|---------|-------|
| ![List](docs/list.png) | ![Loading](docs/loading.png) | ![Error](docs/error.png) |
-->

_Screenshots coming soon._

## Features

- Browse earthquakes reported worldwide in the last 24 hours, fetched live from the USGS GeoJSON feeds.
- Magnitude-based color coding for quick visual scanning of severity.
- Human-readable relative timestamps (e.g. "3 h ago").
- Explicit loading and error states.

## Tech stack

- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Architecture:** MVVM with a data / domain / presentation layer separation
- **Asynchronicity:** Coroutines and Flow (`StateFlow`)
- **Dependency injection:** Hilt
- **Networking:** Retrofit with kotlinx.serialization
- **Min SDK:** 24 · **Target/Compile SDK:** see `app/build.gradle.kts`

## Architecture

The project follows a layered architecture to keep the domain model independent of any framework or data source.

```
presentation  ->  domain  <-  data
```

- **domain** — Framework-agnostic core. Holds the `Earthquake` model and the `EarthquakeRepository` interface. Depends on nothing.
- **data** — Implementation details. Retrofit service, DTOs mapped from the USGS GeoJSON response, and the repository implementation. Maps external DTOs into clean domain models.
- **presentation** — Compose UI and `ViewModel`s. Observes a single immutable `UiState` exposed as a `StateFlow` and renders it. State flows down; events flow up.

The UI depends on the `EarthquakeRepository` **interface**, not its implementation, which keeps the `ViewModel` decoupled from the data source and testable in isolation.

### Data flow

```
UsgsApi (Retrofit)  ->  DTOs  ->  mapper  ->  Earthquake (domain)  ->  UiState  ->  Compose UI
```

## Project structure

```
data/
  remote/        Retrofit service + GeoJSON DTOs
  mapper/        DTO -> domain model mapping
  repository/    EarthquakeRepository implementation
domain/
  model/         Earthquake domain model
  repository/    EarthquakeRepository interface
presentation/
  list/          List screen, ViewModel and UI state
  util/          Formatting helpers
di/              Hilt modules (network, repository bindings)
```

## Getting started

### Prerequisites

- Android Studio (latest stable)
- JDK 17
- An emulator or device running Android 7.0 (API 24) or higher

### Build & run

```bash
git clone https://github.com/sebasgpachano/EarthquakesApp.git
```

Then open the project in Android Studio, let Gradle sync, and run the app on an emulator or device. No API key is required — the USGS feeds are public and unauthenticated.

## API

Data comes from the USGS Earthquake Hazards Program summary feeds, for example:

```
https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson
```

No authentication or API key needed.

## Roadmap

Planned next steps (not yet implemented):

- [ ] Offline caching with Room (single source of truth)
- [ ] Earthquake detail screen with a map centered on the epicenter
- [ ] Navigation between list and detail (Navigation Compose)
- [ ] Filtering by minimum magnitude and time period
- [ ] Pull-to-refresh
- [ ] Unit tests for the `ViewModel` and repository
- [ ] Dark theme polish and color tokens in the theme

## License

[Choose a license — MIT is a common, permissive default. You can add a LICENSE file via GitHub's "Add file" > "Create new file" > type `LICENSE`.]

---

Built by Sebastián.
