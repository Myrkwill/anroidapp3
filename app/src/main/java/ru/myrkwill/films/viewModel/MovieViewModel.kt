package ru.myrkwill.films.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.myrkwill.films.data.moviedetails.MovieDetails
import ru.myrkwill.films.remote.MovieInterface
import ru.myrkwill.films.ui.details.MovieDetailsRepository
import ru.myrkwill.films.ui.movie.MoviePaging
import ru.myrkwill.films.utils.Events
import ru.myrkwill.films.utils.Status
import ru.myrkwill.films.utils.Result
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: MovieInterface,
    private val movieDetailsRepository: MovieDetailsRepository): ViewModel() {

    private val query = MutableLiveData<String>("")

    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails

    val list = query.switchMap {
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(it, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(search: String) {
        query.postValue(search)
    }

    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(movieDetailsRepository.getMovieDetails(imdbId)))
    }
}