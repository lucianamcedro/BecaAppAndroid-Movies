package com.example.moviebeca

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviebeca.client.ClientMovie.Companion.movieClientService
import com.example.moviebeca.databinding.ActivityMovieDetailsBinding
import com.example.moviebeca.model.Movie
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.repositorys.MovieRepository
import com.example.moviebeca.viewmodel.MovieDetailsViewModel
import com.example.moviebeca.viewmodel.MovieDetailsViewModelFactory

class MovieDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val movieRepository = MovieRepository(movieClientService)
    private val movieDetailsFactory = MovieDetailsViewModelFactory(movieRepository)
    private val moviesDetailsViewModel by viewModels<MovieDetailsViewModel> { movieDetailsFactory }

    // private val movieID by lazy {
    //   intent.getIntExtra("id", 0)
    // }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getMovieDetails()
    }

    private fun bindDetails(movieDetails: MovieDetails) {
        binding.movieName.text = movieDetails.title
        binding.movieDescrition.text = movieDetails.overview
        binding.movieVoto.rating = (movieDetails.voteAverage!! / 2).toFloat()

        Glide
            .with(binding.root.context)
            .load("https://image.tmdb.org/t/p/original" + movieDetails.posterPath)
            .centerCrop()
            .into(binding.moviePoster)
    }

    private fun getMovieDetails() {
        // moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movieID)
        // moviesDetailsViewModel.moviesDetails.observe(this) {
        //    movieDetails -> bindDetails(movieDetails)
        val movie: Movie = intent.getSerializableExtra("movie") as Movie
        moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movie.id)
        moviesDetailsViewModel.moviesDetails.observe(this) { movieDetails ->
            bindDetails(movieDetails)
        }
    }
}
