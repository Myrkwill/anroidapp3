package ru.myrkwill.films.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.myrkwill.films.data.MovieResponse

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): Response<MovieResponse>
}