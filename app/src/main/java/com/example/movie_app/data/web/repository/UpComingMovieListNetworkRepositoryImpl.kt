
package com.example.movie_app.data.web.repository

import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.data.web.service.MovieServices
import com.example.movie_app.domain.repository.UpComingMovieListNetworkRepository
import javax.inject.Inject

class UpComingMovieListNetworkRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices
) : UpComingMovieListNetworkRepository {

    override suspend fun getUpComingMovies(page: Int): MovieListResponse {
        return movieServices.getUpComingMovies(page)
    }
}
