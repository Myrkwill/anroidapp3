package ru.myrkwill.films.ui.details

import ru.myrkwill.films.data.moviedetails.MovieDetails
import ru.myrkwill.films.remote.MovieInterface
import ru.myrkwill.films.utils.Status
import ru.myrkwill.films.utils.Result

class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): Result<MovieDetails> {
        return try {
            var response = movieInterface.getMovieDetails(imdbId)
            if (response.isSuccessful) {
                Result(Status.SUCCESS, response.body(), null)
            } else {
                Result(Status.ERROR, null, "")
            }
        } catch (e: java.lang.Exception) {
            Result(Status.ERROR, null, e.message)
        }
    }
}