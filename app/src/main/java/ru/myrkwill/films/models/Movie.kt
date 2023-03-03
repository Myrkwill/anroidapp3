package ru.myrkwill.films.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    var filmId: Int,
    var countries: String?,
    var filmLength: String?,
    var genres: String?,
    var nameEn: String?,
    var nameRu: String?,
    var posterUrl: String?,
    var posterUrlPreview: String?,
    var rating: String?,
    var ratingVoteCount: Int?,
    var year: String?
): Serializable