package com.example.movie_app.domain.usecase

import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.domain.repository.MovieListNetworkRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val movieListNetworkRepository: MovieListNetworkRepository) {
    suspend fun invoke() = flow<UseCaseState<MovieListResponse>> {
        try {
            emit(UseCaseState.Success(movieListNetworkRepository.getMovies()))

        } catch (e: Exception) {
            emit(UseCaseState.Error(e.message.toString()))
        }
    }
}