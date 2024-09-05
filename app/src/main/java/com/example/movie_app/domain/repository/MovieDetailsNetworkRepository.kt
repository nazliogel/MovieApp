package com.example.movie_app.domain.repository

import com.example.movie_app.data.web.model.MovieDetailsResponse

interface MovieDetailsNetworkRepository {
    suspend fun getMovieDetails(movie_id:String) : MovieDetailsResponse
}