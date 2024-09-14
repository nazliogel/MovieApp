package com.example.movie_app.ui.listmovie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie_app.R
import com.example.movie_app.data.web.model.MovieListResponse
import com.example.movie_app.databinding.MovieItemDesignBinding
import com.example.movie_app.utils.Constant

class MovieTopRatedListFragmentAdapter : RecyclerView.Adapter<MovieTopRatedListFragmentAdapter.MovieTopRatedListFragmentViewHolder>() {

    class MovieTopRatedListFragmentViewHolder(val binding: MovieItemDesignBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<MovieListResponse.Result>() {
        override fun areItemsTheSame(
            oldItem: MovieListResponse.Result,
            newItem: MovieListResponse.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListResponse.Result,
            newItem: MovieListResponse.Result
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTopRatedListFragmentViewHolder {
        val binding = MovieItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTopRatedListFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieTopRatedListFragmentViewHolder, position: Int) {
        val listItem = differ.currentList[position]
        Log.d("TopRatedMovieAdapter", "Binding item at position $position: $listItem")

        with(holder.binding) {
            tvMovieName.text = listItem.title
            tvLang.text = listItem.original_language
            tvRate.text = listItem.vote_average.toString()
            tvMovieDateRelease.text = listItem.release_date

            val requestOptions = RequestOptions()
                .override(115, 160)
                .fitCenter()

            Glide.with(root.context)
                .load(Constant.POSTER_BASE_URL + listItem.backdrop_path)
                .apply(requestOptions)
                .into(ImgMovie)

            Log.d("TopRatedMovieAdapter", "Image URL: ${Constant.POSTER_BASE_URL + listItem.backdrop_path}")

            // Tıklama olayını ayarla
            root.setOnClickListener {
                onItemClickListener?.invoke(listItem.id.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
