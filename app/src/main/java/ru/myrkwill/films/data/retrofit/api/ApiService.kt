package ru.myrkwill.films.data.retrofit.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.myrkwill.films.API_KEY
import ru.myrkwill.films.models.ImageData
import ru.myrkwill.films.models.Movies


interface ApiService {

    @Headers("accept: application/json", "X-API-KEY: $API_KEY")
    @GET("films/top?type=TOP_100_POPULAR_FILMS&page=1")
    suspend fun getPopularMovie(): Response<Movies>

    @Headers("accept: application/json", "X-API-KEY: $API_KEY")
    @GET("films/{id}/images?type=POSTER&page=1")
    suspend fun getImageUrls(@Path("id") id: Int): Response<ImageData>
}