package com.example.moviebeca.data.repositorys

<<<<<<< HEAD:app/src/main/java/com/example/moviebeca/data/repositorys/MovieRepository.kt
import com.example.moviebeca.data.client.IClientMovie
import com.example.moviebeca.domain.model.MovieDetails
import com.example.moviebeca.domain.model.MovieResponse
=======
import com.example.moviebeca.client.IClientMovie
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.model.MovieResponse
>>>>>>> a3c7f1d620210c58fe3905c2ac6203452b6fde62:app/src/main/java/com/example/moviebeca/repositorys/MovieRepository.kt
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
