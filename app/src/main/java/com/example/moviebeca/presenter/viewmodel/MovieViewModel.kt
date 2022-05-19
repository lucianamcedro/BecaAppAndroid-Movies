package com.example.moviebeca.presenter.viewmodel

import androidx.lifecycle.*
import com.example.moviebeca.domain.model.Movie
import com.example.moviebeca.data.model.MovieApiResult
import com.example.moviebeca.data.repositorys.IMovieRepository
import com.example.moviebeca.data.repositorys.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.await
import java.lang.Exception

class MovieViewModel(
    private val movieRepository: IMovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<MovieApiResult<List<Movie>>>()
    val movies: LiveData<MovieApiResult<List<Movie>>> = _movie

    fun getMoviesFromRetrofit() {
        viewModelScope.launch {
            _movie.value = MovieApiResult.Loading()
            try {
                val movieApi = movieRepository.getMovies().await()
                _movie.value = MovieApiResult.Success(movieApi.results)
            } catch (e: Exception) {
                val movieResult = MovieApiResult.Error<List<Movie>>(e)
                _movie.value = movieResult
            }
        }
    }
}
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}
