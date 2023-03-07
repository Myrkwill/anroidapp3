package ru.myrkwill.films.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Response") val response: String,
    @SerializedName("Search") val search: List<Movie>,
    val totalResults: String
)