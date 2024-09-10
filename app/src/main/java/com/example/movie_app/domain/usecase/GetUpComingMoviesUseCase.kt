package com.example.movie_app.domain.usecase

import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.domain.repository.MovieListNetworkRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val movieListNetworkRepository: MovieListNetworkRepository
) {
    suspend fun invoke(page:Int) = flow<UseCaseState<MovieListResponse>> {
        try {
            // Burada upcoming filmleri çekecek olan repository fonksiyonunu çağırıyoruz
            emit(UseCaseState.Success(movieListNetworkRepository.getUpComingMovies(page)))
        } catch (e: Exception) {
            emit(UseCaseState.Error(e.message.toString()))
        }
    }
}