package com.example.moviebeca.viewmodel

import androidx.lifecycle.*
import com.example.moviebeca.model.Movie
import com.example.moviebeca.model.MovieApiResult
import com.example.moviebeca.model.MovieDetails
import com.example.moviebeca.repositorys.IMovieRepository
import com.example.moviebeca.repositorys.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.await

class MovieDetailsViewModel(
    private val movieRepository: IMovieRepository
):    ViewModel() {
    private val _moviesDetails = MutableLiveData<MovieApiResult<List<MovieDetails>>>()
    val moviesDetails: LiveData<MovieApiResult<List<MovieDetails>>> = _moviesDetails

    fun getMoviesDetailsFromRetrofit(movieId: Int){
        viewModelScope.launch {
            val details = movieRepository.getDetails(movieId).await()
            _moviesDetails.value = details
        }
    }
}
class MovieDetailsViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(repository) as T
    }

}