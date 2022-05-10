package com.example.moviebeca.viewmodel

import androidx.lifecycle.*
import com.example.moviebeca.model.Movie
import com.example.moviebeca.model.MovieApiResult
import com.example.moviebeca.repositorys.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class MovieViewModel(
  private val movieRepository: IMovieRepository
):    ViewModel() {
    private val _movies = MutableLiveData<MovieApiResult<List<Movie>>>()
    val movies: LiveData<MovieApiResult<List<Movie>>> = _movies

    fun getMoviesFromRetrofit() {
        viewModelScope.launch {
            _movies.value = MovieApiResult.Loading()
            try {
                val moviesFromApi = withContext(Dispatchers.IO) {
                    movieRepository.getMovies()
                }
                _movies.value = MovieApiResult.Success(moviesFromApi)
            } catch (e: Exception) {
                val movieResult = MovieApiResult.Error<List<Movie>>(e)
                _movies.value = movieResult
            }
        }
    }
}

class MovieViewModelFactory(private val repository: IMovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}