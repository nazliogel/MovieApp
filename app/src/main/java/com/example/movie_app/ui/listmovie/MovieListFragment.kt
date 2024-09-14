package com.example.movieapp.ui.listmovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movie_app.base.BaseFragment
import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.databinding.FragmentMovieListBinding
import com.example.movie_app.domain.usecase.UseCaseState
import com.example.movie_app.ui.listmovie.MovieListFragmentAdapter
import com.example.movie_app.ui.listmovie.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment :
    BaseFragment<FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {

    private val viewModel: MovieListViewModel by viewModels()

    private lateinit var movieListAdapter: MovieListFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(p0: View?) {
        // Implement any specific click actions if needed
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupAdapter()
        viewModel.listLiveData.observe(viewLifecycleOwner) { state ->
            handleMovieList(state)
        }
        viewModel.getMovieListUseCaseState()
    }

    private fun setupAdapter() {
        movieListAdapter = MovieListFragmentAdapter().apply {
            setOnItemClickListener { movieId ->
                val navigation = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movieId.toInt())
                findNavController().navigate(navigation)
            }
        }
        binding.onBoardCardView.adapter = movieListAdapter
    }

    private fun handleMovieList(status: UseCaseState<MovieListResponse>) {
        when (status) {
            is UseCaseState.Error -> {
                // Handle the error case
            }
            is UseCaseState.Success -> {
                movieListAdapter.differ.submitList(status.data?.results)
            }
            else -> {
                // Handle other cases
            }
        }
    }
}
