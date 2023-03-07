package ru.myrkwill.films.data.moviedetails

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("Actors") val actors: String,
    @SerializedName("Awards") val awards: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Metascore") val metascore: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Ratings") val ratings: List<Rating>,
    @SerializedName("Released") val released: String,
    @SerializedName("Response") val response: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Year") val year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String,
    val totalSeasons: String
)