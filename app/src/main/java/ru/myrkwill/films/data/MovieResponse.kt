package ru.myrkwill.films.data

data class MovieResponse(
    val response: String,
    val search: List<Movie>,
    val totalResults: String
)