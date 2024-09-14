package com.example.movie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.base.BaseActivity
import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.databinding.ActivityDashBoardBinding
import com.example.movie_app.domain.usecase.UseCaseState
import com.example.movie_app.ui.listmovie.MovieDetailsFragmentAdapter
import com.example.movie_app.ui.listmovie.MovieListFragmentAdapter
import com.example.movie_app.ui.listmovie.MovieListViewModel
import com.example.movie_app.ui.listmovie.MovieTopRatedListFragmentAdapter
import com.example.movie_app.ui.listmovie.MovieUpcomingListFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity :
    BaseActivity<ActivityDashBoardBinding, MovieListViewModel>(ActivityDashBoardBinding::inflate) {

    private val viewModel: MovieListViewModel by viewModels()


    private fun setupAdapter() {
        binding.view1.adapter = movieListAdapter
        movieListAdapter.setOnItemClickListener {
            /* val navigation = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(it.toInt())
             findNavController().navigate(navigation)*/
        }

        binding.view3.adapter = movieUpcomingListListAdapter
        movieListAdapter.setOnItemClickListener {
            /* val navigation = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(it.toInt())
             findNavController().navigate(navigation)*/
        }

        binding.view0.adapter = movieTopRatedListAdapter
        movieTopRatedListAdapter.setOnItemClickListener {
            // Handle item click for top-rated movies
        }

    }

    private val movieListAdapter by lazy {
        MovieListFragmentAdapter()
    }

    private val movieUpcomingListListAdapter by lazy {
        MovieUpcomingListFragmentAdapter()
    }

    private val movieTopRatedListAdapter by lazy {
        MovieTopRatedListFragmentAdapter()
    }



    override fun setupUI() {
        setupAdapter()
        viewModel.getMovieListUseCaseState()
        //çapır upcase
        viewModel.listLiveData.observe(this) {
            handleMovieList(it)
        }

        viewModel.getUpMovieUseCaseState()
        viewModel.upComingListLiveData.observe(this) { result ->
            handleUpcomingMovieList(result)
        }

        viewModel.getTopRatedMoviesUseCaseState()
        viewModel.topRatedListLiveData.observe(this){ result ->
            handleTopRatedMovieList(result)
        }

    }


    private fun handleMovieList(status: UseCaseState<MovieListResponse>) {
        when (status) {
            is UseCaseState.Error -> {
                // close the loading view
            }

            is UseCaseState.Success -> {
                movieListAdapter.differ.submitList(status?.data?.results)
            }

            else -> {
                // close the loading view
            }
        }
    }

    private fun handleUpcomingMovieList(status: UseCaseState<MovieListResponse>) {
        when (status) {
            is UseCaseState.Error -> {
                // Handle error for upcoming movies
            }

            is UseCaseState.Success -> {
                // Update UI with upcoming movies
                // You might need a separate adapter or UI component for upcoming movies
                movieUpcomingListListAdapter.differ.submitList(status.data?.results) // or a different adapter if needed
            }

            else -> {
                // Handle other cases
            }
        }
    }

    private fun handleTopRatedMovieList(status: UseCaseState<MovieListResponse>) {
        when (status) {
            is UseCaseState.Error -> {
                // Handle error for upcoming movies
            }

            is UseCaseState.Success -> {
                // Update UI with upcoming movies
                // You might need a separate adapter or UI component for upcoming movies
                movieTopRatedListAdapter.differ.submitList(status.data?.results) // or a different adapter if needed
            }

            else -> {
                // Handle other cases
            }
        }
    }
}