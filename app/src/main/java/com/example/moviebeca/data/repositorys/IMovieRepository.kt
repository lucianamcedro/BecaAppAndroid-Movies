package com.example.moviebeca.data.repositorys

import com.example.moviebeca.domain.model.MovieDetails
import com.example.moviebeca.domain.model.MovieResponse
import retrofit2.Call

interface IMovieRepository {
    suspend fun getMovies(): Call<MovieResponse>
    suspend fun getDetailsMovies(movieId: Int): Call<MovieDetails>
}
