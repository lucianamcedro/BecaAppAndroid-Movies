package com.example.moviebeca.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviebeca.databinding.MovieListItemBinding
<<<<<<< HEAD:app/src/main/java/com/example/moviebeca/presenter/adapters/MovieItemAdapter.kt
import com.example.moviebeca.domain.model.Movie
=======
import com.example.moviebeca.model.Movie
>>>>>>> a3c7f1d620210c58fe3905c2ac6203452b6fde62:app/src/main/java/com/example/moviebeca/MovieItemAdapter.kt

class MovieItemAdapter(
    var onClickListener: (movie: Movie) -> Unit
) :
    ListAdapter<Movie, MovieItemAdapter.MovieItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieItemViewHolder(
        private val binding: MovieListItemBinding,
        private val onClickListener: (movie: Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

<<<<<<< HEAD:app/src/main/java/com/example/moviebeca/presenter/adapters/MovieItemAdapter.kt
        fun bind(movie: Movie) {
            binding.movieName.text = movie.title
            binding.movieData.text = movie.release_date

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                .centerCrop()
                .into(binding.moviePoster)
=======
<<<<<<< HEAD
        fun bind(movie: Movie) {
            // binding.movieName.contentDescription = movie.title
            // binding.movieData.contentDescription = release_date
            binding.movieName.text = movie.title
            binding.movieData.text = movie.release_date

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                .centerCrop()
                .into(binding.moviePoster)
=======
            fun bind(movie: Movie){
                binding.movieName.text = movie.title
                binding.movieData.text = movie.releaseDate

                Glide
                    .with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                    .centerCrop()
                    .into(binding.moviePoster)
>>>>>>> b1a2234d0603e174d07112e5904d11bf9becf51d
>>>>>>> a3c7f1d620210c58fe3905c2ac6203452b6fde62:app/src/main/java/com/example/moviebeca/MovieItemAdapter.kt

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
<<<<<<< HEAD:app/src/main/java/com/example/moviebeca/presenter/adapters/MovieItemAdapter.kt
=======
<<<<<<< HEAD
=======

>>>>>>> b1a2234d0603e174d07112e5904d11bf9becf51d
>>>>>>> a3c7f1d620210c58fe3905c2ac6203452b6fde62:app/src/main/java/com/example/moviebeca/MovieItemAdapter.kt
}
