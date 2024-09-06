package com.example.movie_app.ui.listmovie

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
import com.example.movie_app.utils.Constant


class MovieListFragmentAdapter: RecyclerView.Adapter<MovieListFragmentAdapter.MovieActivityViewHolder>() {

    class MovieActivityViewHolder(view: View): RecyclerView.ViewHolder(view)


    private val differCallback = object : DiffUtil.ItemCallback<MovieListResponse.Result>(){
        override fun areItemsTheSame(
            oldItem: MovieListResponse.Result,
            newItem: MovieListResponse.Result
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieListResponse.Result,
            newItem: MovieListResponse.Result
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieActivityViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_item_design, parent, false)
        return MovieActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieActivityViewHolder, position: Int) {
        val listItem = differ.currentList[position]

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}