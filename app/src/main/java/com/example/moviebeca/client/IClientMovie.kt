package com.example.moviebeca.client

import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.model.MovieResponse
import com.example.moviebeca.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IClientMovie {

    companion object {
        private val API_KEY = "d0424b3b44e681c7d399af0fb11d6091"
    }
    @GET("trending/movie/week$API_KEY")
    fun getBreeds(): Call<MovieResponse>

    @GET("movie/{movie_id}$API_KEY")
    fun getDetailsMovies(@Path("movie_id") movieId: Int): Call<MovieDetails>

}