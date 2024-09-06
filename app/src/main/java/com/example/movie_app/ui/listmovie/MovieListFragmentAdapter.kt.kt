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

class MovieListFragmentAdapter : RecyclerView.Adapter<MovieListFragmentAdapter.MovieListFragmentViewHolder>() {

    class MovieListFragmentViewHolder(val binding: MovieItemDesignBinding) : RecyclerView.ViewHolder(binding.root)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListFragmentViewHolder {
        val binding = MovieItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListFragmentViewHolder, position: Int) {
        val listItem = differ.currentList[position]
        Log.d("MovieListAdapter", "Binding item at position $position: $listItem")

        with(holder.binding) {
            tvMovieName.text = listItem.title
            tvLang.text = listItem.originalLanguage
            tvRate.text = listItem.voteAverage.toString()
            tvMovieDateRelease.text = listItem.releaseDate

            val requestOptions = RequestOptions()
                .override(115, 160)
                .fitCenter()

            Glide.with(root.context)
                .load(Constant.POSTER_BASE_URL + listItem.backdropPath)
                .apply(requestOptions)
                .into(ImgMovie)

            Log.d("MovieListAdapter", "Image URL: ${Constant.POSTER_BASE_URL + listItem.backdropPath}")


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