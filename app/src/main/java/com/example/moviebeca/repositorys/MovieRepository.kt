package com.example.moviebeca.repositorys

import com.example.moviebeca.client.IClientMovie
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MovieRepository(private val movieClient: IClientMovie) : IMovieRepository {
    override suspend fun getMovies(): Call<MovieResponse> {
        return withContext(Dispatchers.IO) {
            movieClient.getBreeds()
        }
    }

    override suspend fun getDetailsMovies(movieID: Int): Call<MovieDetails> {
        return withContext(Dispatchers.IO) {
            movieClient.getDetailsMovies(movieId = movieID)
        }
    }
}
