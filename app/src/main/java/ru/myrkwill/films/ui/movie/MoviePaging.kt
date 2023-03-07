package ru.myrkwill.films.ui.movie

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.myrkwill.films.data.Movie
import ru.myrkwill.films.remote.MovieInterface
import ru.myrkwill.films.utils.Constants

class MoviePaging(
    private val search: String,
    private val movieInterface: MovieInterface): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val data = movieInterface.getAllMovies(search, page, Constants.API_KEY)
            Log.d("TAG", "load: ${data.body()}")
            LoadResult.Page(
                data = data.body()?.search!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.search?.isEmpty()!!) null else page + 1
            )
        } catch (e: java.lang.Exception) {
            Log.d("TAG", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }
}