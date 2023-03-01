package ru.myrkwill.films.views.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.myrkwill.films.data.retrofit.RetrofitRepository
import ru.myrkwill.films.models.Movies

class MainFragmentViewModel: ViewModel() {

    private val repository = RetrofitRepository()
    val movies: MutableLiveData<Response<Movies>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            movies.value = repository.getMovies()
        }
    }
}