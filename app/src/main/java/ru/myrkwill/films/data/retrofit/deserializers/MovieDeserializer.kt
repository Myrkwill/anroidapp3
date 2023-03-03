package ru.myrkwill.films.data.retrofit.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.myrkwill.films.models.Movie
import java.lang.reflect.Type

class MovieDeserializer : JsonDeserializer<Movie> {

    override fun deserialize(
        json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?
    ): Movie {
        val jsonObject = json?.asJsonObject
        return Movie(
            filmId = jsonObject?.get("filmId")?.asInt ?: 0,
            countries = jsonObject?.get("countries")?.asJsonArray?.map { it.asJsonObject }
                ?.joinToString { it["country"].asString } ?: "",
            filmLength = jsonObject?.get("filmLength")?.asStringNullable ?: "",
            genres = jsonObject?.get("genres")?.asJsonArray?.map { it.asJsonObject }
                ?.joinToString { it["genre"].asStringNullable } ?: "",
            nameEn = jsonObject?.get("nameEn")?.asStringNullable ?: "",
            nameRu = jsonObject?.get("nameRu")?.asStringNullable ?: "",
            posterUrl = jsonObject?.get("posterUrl")?.asStringNullable ?: "",
            posterUrlPreview = jsonObject?.get("posterUrlPreview")?.asStringNullable ?: "",
            rating = jsonObject?.get("rating")?.asStringNullable ?: "",
            ratingVoteCount = jsonObject?.get("ratingVoteCount")?.asInt ?: 0,
            year = jsonObject?.get("year")?.asStringNullable ?: ""
        )
    }

}

var JsonElement.asStringNullable: String
    get() {
        return if (!isJsonNull) asString else ""
    }
    set(value) {}