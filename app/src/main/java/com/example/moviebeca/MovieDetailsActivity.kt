package com.example.moviebeca

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviebeca.client.IClientMovie
import com.example.moviebeca.databinding.ActivityMovieDetailsBinding
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.repositorys.MovieRepository
import com.example.moviebeca.viewmodel.MovieDetailsViewModel
import com.example.moviebeca.viewmodel.MovieDetailsViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieDetailsActivity: AppCompatActivity(){

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val movieClient: IClientMovie by lazy {
        retrofit.create(IClientMovie::class.java)
    }

    private val movieRepository = MovieRepository(movieClient)
    private val movieDetailsFactory = MovieDetailsViewModelFactory(movieRepository)
    private val moviesDetailsViewModel by viewModels<MovieDetailsViewModel>{movieDetailsFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        getMovieDetails()
    }

    fun bindDetails(movieDetails: MovieDetails){
        binding.movieName.text = movieDetails.title
        binding.movieData.text = movieDetails.release_date

        Glide
            .with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w500/")
            .centerCrop()
            .into(binding.moviePoster)
    }
}

fun getMovieDetails(){
    MovieDetailsViewModel.getMoviesDetailsFromRetrofit(movie.id)
    movieDetailsViewModel.movieDetails.observe(this) {
            movieDetails -> bindDetails(movieDetails)
    }
}

