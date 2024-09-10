package com.example.movie_app.domain.repository

import com.example.movie_app.data.web.model.MovieListResponse

interface MovieListNetworkRepository {
    suspend fun getMovies(): MovieListResponse

    suspend fun getUpComingMovies(page:Int): MovieListResponse
}