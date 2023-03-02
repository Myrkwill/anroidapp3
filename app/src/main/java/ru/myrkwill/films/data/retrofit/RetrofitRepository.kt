package ru.myrkwill.films.data.retrofit

import android.util.Log
import retrofit2.Response
import ru.myrkwill.films.data.retrofit.api.RetrofitInstance
import ru.myrkwill.films.models.Movies

class RetrofitRepository {

    suspend fun getMovies(): Response<Movies> {
        val movies = RetrofitInstance.api.getPopularMovie()
        movies.body()?.let {
            it.films.forEach { movie ->
                val imageData = RetrofitInstance.api.getImageUrls(movie.filmId)
                movie.posterUrl = imageData.body()?.items?.first()?.imageUrl ?: ""
                movie.posterUrlPreview = imageData.body()?.items?.first()?.previewUrl ?: ""
            }
        }

        return movies
    }
}