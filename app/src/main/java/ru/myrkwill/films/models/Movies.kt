package ru.myrkwill.films.models

data class Movies(
    var pagesCount: Int = 0,
    var films: List<Movie> = emptyList()
)