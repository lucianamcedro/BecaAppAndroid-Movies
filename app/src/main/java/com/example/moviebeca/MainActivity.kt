package com.example.moviebeca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.moviebeca.client.IClientMovie
import com.example.moviebeca.client.IClientMovie.Companion.baseUrl
import com.example.moviebeca.databinding.ActivityMainBinding
import com.example.moviebeca.model.Movie
import com.example.moviebeca.model.MovieApiResult
import com.example.moviebeca.repositorys.MovieRepository
import com.example.moviebeca.viewmodel.MovieViewModel
import com.example.moviebeca.viewmodel.MovieViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val movieClient:IClientMovie by lazy {
        retrofit.create(IClientMovie::class.java)
    }


    private val movieListAdapter by lazy {
        MovieItemAdapter(onClickListener = { movie ->
            goToMovieDetails(movie)
        })
    }
    private val movieRepository = MovieRepository(movieClient)
    private val movieFactory = MovieViewModelFactory(movieRepository)
    private val movieViewModel by viewModels<MovieViewModel>{movieFactory}

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
            //setupAdapter(listMovies)
        //}
    //}
             private fun getMoviesAndObserve() {
                 movieViewModel.getMoviesFromRetrofit()
                 movieViewModel.movies.observe(this) { movieApiResult ->
                     when(movieApiResult) {
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


    private fun setupAdapter(list: List<Movie>){
        movieListAdapter.submitList(list)
    }

    private fun goToMovieDetails(movieId: Int){
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }

}
