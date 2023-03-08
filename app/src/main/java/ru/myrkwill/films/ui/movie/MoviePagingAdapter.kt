package ru.myrkwill.films.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.myrkwill.films.data.Movie
import ru.myrkwill.films.databinding.ViewHolderMovieBinding

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(DIFF_UTIL) {

    private var onClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    fun onMovieClick(listener: (String) -> Unit) {
        onClick = listener
    }

    inner class ViewHolder(private val binding: ViewHolderMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            movieTitle.text = movie.title
            movieDescription.text = "Year: ${movie.year}"
            Glide.with(movieImage.context)
                .load(movie.poster)
                .centerCrop()
                .into(movieImage)
            root.setOnClickListener {
                onClick?.let { it(movie.imdbID) }
            }
        }

    }

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
               return oldItem == newItem
            }
        }
    }
}