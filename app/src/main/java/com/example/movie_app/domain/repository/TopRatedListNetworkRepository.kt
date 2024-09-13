package com.example.movie_app.domain.repository

import com.example.movie_app.data.web.model.MovieListResponse

interface TopRatedListNetworkRepository {
    suspend fun getTopRatedMovies(page: Int): MovieListResponse
}