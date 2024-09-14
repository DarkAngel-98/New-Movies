package com.solo.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solo.common.network.domain.models.movie.Movie
import com.solo.home.databinding.MovieItemRowBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieAdapter @Inject constructor() :
    ListAdapter<Movie, RecyclerView.ViewHolder>(
        fileItemDiffCallback
    ) {

    companion object {
        val fileItemDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as CategoryViewHolder).bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CategoryViewHolder(
            MovieItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class CategoryViewHolder(private val binding: MovieItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(movie: Movie) {
            binding.apply {
                Picasso.get()
                    .load(movie.backdropPath).into(ivCoverPhoto)
                tvMovieName.text = movie.title
                tvRating.text = binding.root.context.getString(
                    com.solo.common.R.string.rating_is,
                    movie.voteAverage.toString()
                )
                tvDescription.text = movie.overview

            }
        }
    }
}