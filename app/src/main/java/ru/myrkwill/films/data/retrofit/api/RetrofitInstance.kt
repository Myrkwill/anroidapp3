package ru.myrkwill.films.data.retrofit.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myrkwill.films.BASE_URL
import ru.myrkwill.films.data.retrofit.deserializers.MovieDeserializer
import ru.myrkwill.films.models.Movie
import ru.myrkwill.films.models.Movies

object RetrofitInstance {

    private val retrofit by lazy {
        val movieDeserializer =
            GsonBuilder().registerTypeAdapter(Movie::class.java, MovieDeserializer()).create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(movieDeserializer))
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}