package com.example.moviebeca.presenter.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviebeca.data.client.ClientMovie.Companion.movieClientService
import com.example.moviebeca.databinding.ActivityMainBinding
import com.example.moviebeca.domain.model.Movie
import com.example.moviebeca.data.model.MovieApiResult
import com.example.moviebeca.presenter.adapters.MovieItemAdapter
import com.example.moviebeca.data.repositorys.MovieRepository
import com.example.moviebeca.presenter.viewmodel.MovieViewModel
import com.example.moviebeca.presenter.viewmodel.MovieViewModelFactory

class MainActivity : AppCompatActivity() {

    private val movieListAdapter by lazy {
        MovieItemAdapter(onClickListener = { movie ->
            goToMovieDetails(movie)
        })
    }
    private val movieRepository = MovieRepository(movieClientService)
    private val movieFactory = MovieViewModelFactory(movieRepository)
    private val movieViewModel by viewModels<MovieViewModel> { movieFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.movieListRecyclerview.adapter = movieListAdapter
        movieViewModel.getMoviesFromRetrofit()
        //  setListAdapter(mockMovie())

        getMoviesAndObserve()
    }
    // fun getMovies(){
    //   lifecycleScope.launch {
    //     var listMovies: List<Movie>
    //   withContext(Dispatchers.IO){
    //     var result = movieClient.getListMovies()

    //   listMovies= result
    // }
    // setupAdapter(listMovies)
    // }
    // }
    private fun getMoviesAndObserve() {
        movieViewModel.getMoviesFromRetrofit()
        movieViewModel.movies.observe(this) { movieApiResult ->
            when (movieApiResult) {
                is MovieApiResult.Loading -> {
                    Log.d("INFO", "Loading")
                }
                is MovieApiResult.Success -> {
                    Log.d("INFO", "Success")
                    setupAdapter(movieApiResult.data)
                }
                is MovieApiResult.Error -> {
                    Log.d("INFO", "Error: ${movieApiResult.throwable.cause}")
                }
            }
        }
    }

    private fun goToMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
    private fun setupAdapter(list: List<Movie>) {
        movieListAdapter.submitList(list)
    }
}


