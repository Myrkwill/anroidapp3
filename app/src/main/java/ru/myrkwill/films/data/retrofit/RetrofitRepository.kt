package ru.myrkwill.films.data.retrofit

import android.util.Log
import retrofit2.Response
import ru.myrkwill.films.data.retrofit.api.RetrofitInstance
import ru.myrkwill.films.models.Movies

class RetrofitRepository {

    suspend fun getMovies(): Response<Movies> {
        val movies = RetrofitInstance.api.getPopularMovie()
        Log.i("Tag", movies.body()?.films.toString())
        return movies
    }
}