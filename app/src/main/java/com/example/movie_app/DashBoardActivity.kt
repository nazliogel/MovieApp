package com.example.movie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.movie_app.databinding.ActivityDashBoardBinding
import com.example.movie_app.ui.listmovie.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding
    private val viewModel: MovieListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        binding=ActivityDashBoardBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}