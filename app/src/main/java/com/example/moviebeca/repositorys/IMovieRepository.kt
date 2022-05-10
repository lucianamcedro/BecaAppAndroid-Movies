package com.example.moviebeca.repositorys

import com.example.moviebeca.model.Movie
import com.example.moviebeca.model.MovieDetails
import retrofit2.Call

interface IMovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getDetails(movieId: Int): Call<MovieDetails>
}