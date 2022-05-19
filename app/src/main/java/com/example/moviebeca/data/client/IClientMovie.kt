package com.example.moviebeca.data.client

<<<<<<< HEAD:app/src/main/java/com/example/moviebeca/data/client/IClientMovie.kt
import com.example.moviebeca.domain.model.MovieDetails
import com.example.moviebeca.domain.model.MovieResponse
=======
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.model.MovieResponse
>>>>>>> a3c7f1d620210c58fe3905c2ac6203452b6fde62:app/src/main/java/com/example/moviebeca/client/IClientMovie.kt
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IClientMovie {

    companion object {
        const val API_KEY = "?api_key=d0424b3b44e681c7d399af0fb11d6091&page=1&language=pt-BR"
        const val baseUrl = "https://api.themoviedb.org/3/"
    }
    @GET("trending/movie/week$API_KEY")
    fun getBreeds(): Call<MovieResponse>

    @GET("movie/{movie_id}$API_KEY")
    fun getDetailsMovies(@Path("movie_id") movieId: Int): Call<MovieDetails>
}
