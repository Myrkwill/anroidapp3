package ru.myrkwill.films.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myrkwill.films.remote.MovieInterface
import ru.myrkwill.films.ui.details.MovieDetailsRepository
import ru.myrkwill.films.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideRepository(movieInterface: MovieInterface): MovieDetailsRepository {
        return MovieDetailsRepository(movieInterface)
    }

    @Provides
    @Singleton
    fun provideRetrofitInterface(
        client: OkHttpClient
    ): MovieInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieInterface::class.java)
    }

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
            val url = request.url()
                .newBuilder()
                .addQueryParameter("apikey", Constants.API_KEY)
                .build()
            val newRequest = request.newBuilder().url(url).build()
            it.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

}