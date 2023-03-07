package ru.myrkwill.films.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.myrkwill.films.remote.MovieInterface
import ru.myrkwill.films.ui.movie.MoviePaging
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface): ViewModel() {

    private val query = MutableLiveData<String>("")

    val list = query.switchMap {
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(it, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(search: String) {
        query.postValue(search)
    }
}