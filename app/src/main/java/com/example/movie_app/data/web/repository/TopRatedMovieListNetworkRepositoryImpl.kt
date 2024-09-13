package com.example.movie_app.data.web.repository

import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.data.web.service.MovieServices
import com.example.movie_app.domain.repository.TopRatedListNetworkRepository
import com.example.movie_app.domain.repository.UpComingMovieListNetworkRepository
import javax.inject.Inject

class TopRatedMovieListNetworkRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices
) : TopRatedListNetworkRepository {

    override suspend fun getTopRatedMovies(page: Int): MovieListResponse {
        return movieServices.getTopRatedMovies(page)
    }
}