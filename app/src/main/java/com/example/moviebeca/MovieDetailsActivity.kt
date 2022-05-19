package com.example.moviebeca

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
<<<<<<< HEAD
import com.example.moviebeca.client.ClientMovie.Companion.movieClientService
=======
import com.example.moviebeca.client.IClientMovie
import com.example.moviebeca.client.IClientMovie.Companion.baseUrl
>>>>>>> b1a2234d0603e174d07112e5904d11bf9becf51d
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

<<<<<<< HEAD
    private val movieRepository = MovieRepository(movieClientService)
=======
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val movieClient: IClientMovie by lazy {
        retrofit.create(IClientMovie::class.java)
    }

    private val movieRepository = MovieRepository(movieClient)
>>>>>>> b1a2234d0603e174d07112e5904d11bf9becf51d
    private val movieDetailsFactory = MovieDetailsViewModelFactory(movieRepository)
    private val moviesDetailsViewModel by viewModels<MovieDetailsViewModel> { movieDetailsFactory }

    // private val movieID by lazy {
    //   intent.getIntExtra("id", 0)
    // }

   // private val movieID by lazy {
     //   intent.getIntExtra("id", 0)
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getMovieDetails()
    }

<<<<<<< HEAD
    private fun bindDetails(movieDetails: MovieDetails) {
        binding.movieName.text = movieDetails.title
        binding.movieDescrition.text = movieDetails.overview
        //   binding.movieName.contentDescription = movieDetails.title
        // binding.movieDescrition.contentDescription = movieDetails.overview
        binding.movieVoto.rating = (movieDetails.voteAverage!! / 2).toFloat()

        Glide
            .with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w500/" + movieDetails.backdropPath)
=======
    private fun bindDetails(movieDetails: MovieDetails){
        binding.movieName.text = movieDetails.title
        binding.movieVoto.rating = movieDetails.voteAverange
        binding.movieDescrition.text = movieDetails.overview

        Glide
            .with(binding.root.context)
            .load("https://image.tmdb.org/t/p/original" + movieDetails.posterPath)
>>>>>>> b1a2234d0603e174d07112e5904d11bf9becf51d
            .centerCrop()
            .into(binding.moviePoster)
    }

    private fun getMovieDetails() {
<<<<<<< HEAD
        // moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movieID)
        // moviesDetailsViewModel.moviesDetails.observe(this) {
        //    movieDetails -> bindDetails(movieDetails)
        val movie: Movie = intent.getSerializableExtra("movie") as Movie
        moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movie.id)
        moviesDetailsViewModel.moviesDetails.observe(this) { movieDetails ->
            bindDetails(movieDetails)
=======
       // moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movieID)
       // moviesDetailsViewModel.moviesDetails.observe(this) {
            //    movieDetails -> bindDetails(movieDetails)

            val movie: Movie = intent.getSerializableExtra("movie") as Movie
            moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movie)
            moviesDetailsViewModel.moviesDetails.observe(this) { movieDetails ->
                bindDetails(movieDetails)
>>>>>>> b1a2234d0603e174d07112e5904d11bf9becf51d
        }
    }
}
