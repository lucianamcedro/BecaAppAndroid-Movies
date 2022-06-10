package com.example.moviebeca.data.Dao

import android.content.Context
import androidx.room.Room
import com.example.moviebeca.domain.model.Movie

class MovieDaoRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(contextCoin: Context) {
            context = contextCoin
        }
    }

    fun loadDatabase(id: Int): Movie {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        return databaseMovie.movieDao().load(id)
    }

    fun addFavorite(movie: Movie) {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        databaseMovie.movieDao().save(movie)
    }

    fun deleteFavorite(movieId: Movie) {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        return databaseMovie.movieDao().delete(movieId)
    }

    fun listFavorite(): List<Movie> {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        return databaseMovie.movieDao().getInvited()
    }
}