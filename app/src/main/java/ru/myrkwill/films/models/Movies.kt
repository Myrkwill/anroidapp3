package ru.myrkwill.films.models

data class Movies(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalMovies: Int
)