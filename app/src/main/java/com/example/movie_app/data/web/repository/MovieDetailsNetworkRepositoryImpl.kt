package com.example.movie_app.data.web.repository

import com.example.movie_app.data.web.model.MovieDetailsResponse
import com.example.movie_app.data.web.service.MovieServices
import com.example.movie_app.domain.repository.MovieDetailsNetworkRepository
import javax.inject.Inject

class MovieDetailsNetworkRepositoryImpl @Inject constructor (
    private val movieDetailsServices : MovieServices
) : MovieDetailsNetworkRepository {

    //this is implemented by override
    override suspend fun getMovieDetails(movie_id : String): MovieDetailsResponse {
        return movieDetailsServices.getMovieDetails(movie_id)
    }

}