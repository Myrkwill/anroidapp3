package ru.myrkwill.films.models

data class Movie(
    val countries: List<Country>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<Genre>,
    val nameEn: String,
    val nameRu: String,
    var posterUrl: String,
    var posterUrlPreview: String,
    val rating: String,
    val ratingVoteCount: Int,
    val year: String
)