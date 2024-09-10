package com.example.movie_app.data.web.repository

import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.data.web.service.MovieServices
import com.example.movie_app.domain.repository.MovieListNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListNetworkRepositoryImpl @Inject constructor(
    private val movieListServices: MovieServices
) : MovieListNetworkRepository {
    override suspend fun getMovies(): MovieListResponse = withContext(Dispatchers.IO) {
        movieListServices.getMovies()
    }

    override suspend fun getUpComingMovies(page:Int): MovieListResponse {
        return movieListServices.getUpComingMovies(page)
    }



}