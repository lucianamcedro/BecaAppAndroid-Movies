package com.example.moviebeca

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviebeca.databinding.MovieListItemBinding
import com.example.moviebeca.model.Movie
import kotlinx.coroutines.NonDisposableHandle.parent

class MovieItemAdapter(
    var onClickListener: ((movieId: Int) -> Unit)?
): ListAdapter<Movie, MovieItemAdapter.MovieItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class MovieItemViewHolder(
        private val binding: MovieListItemBinding,
        private val onClickListener: ((movieId: Int) -> Unit)?
        ):RecyclerView.ViewHolder(binding.root) {

            fun bind(movie: Movie){
                binding.movieName.text = movie.title
                binding.movieData.text = movie.releaseDate

                Glide
                    .with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                    .centerCrop()
                    .into(binding.moviePoster)

                binding.root.setOnClickListener {
                    onClickListener?.invoke(movie.id)
                }
            }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

}
