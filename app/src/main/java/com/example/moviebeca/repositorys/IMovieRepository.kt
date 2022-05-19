package com.example.moviebeca.repositorys

import com.example.moviebeca.model.Movie
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.model.MovieResponse
import retrofit2.Call

interface IMovieRepository {
    suspend fun getMovies(): Call<MovieResponse>
    suspend fun getDetailsMovies(movieId: Int): Call<MovieDetails>
}