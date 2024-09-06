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
import com.example.movie_app.ui.listmovie.MovieListFragmentAdapter
import com.example.movie_app.ui.listmovie.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, MovieListViewModel>(ActivityDashBoardBinding::inflate) {

    private val viewModel: MovieListViewModel by viewModels()


    private fun setupAdapter() {
        binding.view1.adapter = movieListAdapter
        movieListAdapter.setOnItemClickListener {
           /* val navigation = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(it.toInt())
            findNavController().navigate(navigation)*/
        }
    }

    private val movieListAdapter by lazy {
        MovieListFragmentAdapter()
    }

    override fun setupUI() {
        setupAdapter()
        viewModel.getMovieListUseCaseState()
        viewModel.listLiveData.observe(this) {
            handleMovieList(it)
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


}