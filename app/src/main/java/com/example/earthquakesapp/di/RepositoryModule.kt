package com.example.earthquakesapp.di

import com.example.earthquakesapp.data.repository.EarthquakeRepositoryImpl
import com.example.earthquakesapp.domain.repository.EarthquakeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEarthquakeRepository(
        impl: EarthquakeRepositoryImpl
    ) : EarthquakeRepository
}