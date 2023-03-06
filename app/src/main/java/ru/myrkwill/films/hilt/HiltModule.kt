package ru.myrkwill.films.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myrkwill.films.remote.MovieInterface
import ru.myrkwill.films.utils.Constants

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieInterface::class.java)
    }

}