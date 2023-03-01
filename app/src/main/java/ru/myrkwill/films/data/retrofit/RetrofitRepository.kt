package ru.myrkwill.films.data.retrofit

import retrofit2.Response
import ru.myrkwill.films.data.retrofit.api.RetrofitInstance

class RetrofitRepository {

    suspend fun getMovies(): Response<Movies> {
        return RetrofitInstance.api.getPopularMovie()
    }
}