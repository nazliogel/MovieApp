package com.example.movie_app.ui.listmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.domain.usecase.GetMovieListUseCase
import com.example.movie_app.domain.usecase.GetTopRatedMoviesUseCase
import com.example.movie_app.domain.usecase.GetUpcomingMoviesUseCase
import com.example.movie_app.domain.usecase.UseCaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val listMutableLiveData = MutableLiveData<UseCaseState<MovieListResponse>>()
    val listLiveData: LiveData<UseCaseState<MovieListResponse>> get() = listMutableLiveData

    private val upComingListMutableLiveData = MutableLiveData<UseCaseState<MovieListResponse>>()
    val upComingListLiveData: LiveData<UseCaseState<MovieListResponse>> get() = upComingListMutableLiveData

    private val topRatedListMutableLiveData = MutableLiveData<UseCaseState<MovieListResponse>>()
    val topRatedListLiveData: LiveData<UseCaseState<MovieListResponse>> get() = topRatedListMutableLiveData

    // Mevcut film listesini getiren fonksiyon
    fun getMovieListUseCaseState() {
        viewModelScope.launch {
            getMovieListUseCase.invoke(page = 1).collect { it ->
                when (it) {
                    is UseCaseState.Success -> {
                        listMutableLiveData.value = it
                    }
                    is UseCaseState.Error -> {
                        Log.i(it.message, "")
                    }
                    else -> {}
                }
            }
        }
    }

    // Yaklaşan filmleri getiren fonksiyon
    fun getUpMovieUseCaseState() {
        viewModelScope.launch {
            getUpcomingMoviesUseCase.invoke(page = 2).collect { it ->
                when (it) {
                    is UseCaseState.Success -> {
                        upComingListMutableLiveData.value = it
                    }
                    is UseCaseState.Error -> {
                        Log.i(it.message, "")
                    }
                    else -> {}
                }
            }
        }
    }

    fun getTopRatedMoviesUseCaseState() {
        viewModelScope.launch {
            getTopRatedMoviesUseCase.invoke(page = 1).collect { it ->
                when (it) {
                    is UseCaseState.Success -> {
                        topRatedListMutableLiveData.value = it
                    }
                    is UseCaseState.Error -> {
                        Log.i(it.message, "")
                    }
                    else -> {}
                }
            }
        }
    }

}
