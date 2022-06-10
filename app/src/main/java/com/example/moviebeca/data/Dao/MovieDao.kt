package com.example.moviebeca.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviebeca.domain.model.Movie

@Dao
interface MovieDao {

    @Insert
    fun save(movie: Movie)

    @Delete
    fun delete(movieId: Movie)

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun load(id: Int): Movie

    @Query("SELECT * FROM Movie")
    fun getInvited(): List<Movie>
}