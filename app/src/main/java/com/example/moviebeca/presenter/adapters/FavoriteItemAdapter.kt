package com.example.moviebeca.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviebeca.databinding.FavoriteListItemBinding
import com.example.moviebeca.domain.model.Movie

class FavoriteItemAdapter(
    var onClickListener: (movie: Movie) -> Unit
) :
    ListAdapter<Movie, FavoriteItemAdapter.FavoriteMovieItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieItemViewHolder {
        val binding =
            FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: FavoriteMovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteMovieItemViewHolder(
        private val binding: FavoriteListItemBinding,
        private val onClickListener: (movie: Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieName.text = movie.title
            binding.movieData.text = movie.release_date

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                .centerCrop()
                .into(binding.moviePoster)

            binding.root.setOnClickListener {
                onClickListener.invoke(movie)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}