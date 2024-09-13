package com.example.movie_app.domain.usecase

import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.domain.repository.MovieListNetworkRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val movieListNetworkRepository: MovieListNetworkRepository
) {
    // Suspend function kullanarak top-rated filmleri çekiyoruz
    suspend fun invoke(page: Int) = flow<UseCaseState<MovieListResponse>> {
        try {
            // Repository'deki top-rated movies fonksiyonunu çağırıyoruz
            val topRatedMovies = movieListNetworkRepository.getTopRated(page)
            emit(UseCaseState.Success(topRatedMovies))
        } catch (e: Exception) {
            emit(UseCaseState.Error(e.message.toString()))
        }
    }
}
