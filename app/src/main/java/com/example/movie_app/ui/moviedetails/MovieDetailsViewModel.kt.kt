package com.example.movie_app.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.web.model.MovieDetailsResponse
import com.example.movie_app.domain.usecase.GetMovieDetailsUseCase
import com.example.movie_app.domain.usecase.UseCaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _listDetailsLiveData = MutableLiveData<MovieDetailsResponse?>()
    val listDetailsLiveData: LiveData<MovieDetailsResponse?> = _listDetailsLiveData


    fun getMovieDetailsUseCaseState(movieId: String) {
        viewModelScope.launch {
            getMovieDetailsUseCase.execute(movieId).collect { result ->
                when (result) {
                    is UseCaseState.Success -> {
                        _listDetailsLiveData.postValue(result.data)
                    }
                    is UseCaseState.Error -> {
                        // Hata yönetimi yapabilirsiniz
                    }
                }
            }
        }
    }
}
