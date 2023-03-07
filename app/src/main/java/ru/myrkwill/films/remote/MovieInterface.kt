package ru.myrkwill.films.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.myrkwill.films.data.MovieResponse
import ru.myrkwill.films.data.moviedetails.MovieDetails

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") search: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}