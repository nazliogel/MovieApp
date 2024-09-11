package com.example.movie_app.movieapp.di

import com.example.movie_app.data.web.repository.MovieDetailsNetworkRepositoryImpl
import com.example.movie_app.data.web.repository.MovieListNetworkRepositoryImpl
import com.example.movie_app.domain.repository.MovieDetailsNetworkRepository
import com.example.movie_app.domain.repository.MovieListNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindMovieListRepository(movieListNetworkRepositoryImpl: MovieListNetworkRepositoryImpl) : MovieListNetworkRepository

    @Singleton
    @Binds
    abstract fun bindMovieDetailsRepository(movieDetailsNetworkRepositoryImpl: MovieDetailsNetworkRepositoryImpl) : MovieDetailsNetworkRepository


}