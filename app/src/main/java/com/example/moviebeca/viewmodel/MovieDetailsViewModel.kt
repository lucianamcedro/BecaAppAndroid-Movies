package com.example.moviebeca.viewmodel

import androidx.lifecycle.*
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.repositorys.IMovieRepository
import com.example.moviebeca.repositorys.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.await

class MovieDetailsViewModel(private val movieRepository: IMovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetails>()
    val moviesDetails: LiveData<MovieDetails> = _movieDetails

    fun getMoviesDetailsFromRetrofit(movieId: Int) {

        viewModelScope.launch {
            val details = movieRepository.getDetailsMovies(movieId).await()
            _movieDetails.value = details
        }
    }
}
class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(repository) as T
    }
}
