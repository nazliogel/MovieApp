package com.example.movie_app.ui.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie_app.base.BaseFragment
import com.example.movie_app.databinding.MovieDetailDesignBinding
import com.example.movie_app.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.ln
import kotlin.math.pow

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailDesignBinding>(MovieDetailDesignBinding::inflate) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun setupUI(savedInstanceState: Bundle?) {
        // Film detaylarını id ile al
        viewModel.getMovieDetailsUseCaseState(args.movieId.toString())
        // Detayları gözlemlemeye başla
        setupObserve()
    }

    // Canlı veri gözlemi ve veriyi UI'ya aktarma
    private fun setupObserve() {
        viewModel.listDetailsLiveData.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                with(binding) {
                    movieTitle.text = movie.originalTitle // Başlık
                    tvReleaseDate.text = movie.releaseDate // Yayın tarihi
                    tvRating.text = movie.voteAverage?.format(1) // Puan
                    movieOverview.text = movie.overview // Film açıklaması

                    // Posteri yükle ve göster
                    Glide.with(this@MovieDetailsFragment)
                        .setDefaultRequestOptions(RequestOptions().override(200, 300).fitCenter())
                        .load(Constant.POSTER_BASE_URL + movie.backdropPath)
                        .into(moviePoster)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        // Eğer UI üzerinde tıklanacak başka elemanlar varsa, buraya eklersin.
    }

    // Puanlama formatı
    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    // Sayıları okunabilir hale getirme
    private fun getFormattedNumber(count: Long): String {
        if (count < 1000) return "$count"
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}
