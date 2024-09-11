package com.example.movie_app.domain.repository

import com.example.movie_app.data.web.model.MovieListResponse

interface UpComingMovieListNetworkRepository {
    suspend fun getUpComingMovies(page: Int): MovieListResponse

}
