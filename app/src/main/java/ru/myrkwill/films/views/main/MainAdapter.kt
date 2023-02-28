package ru.myrkwill.films.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.myrkwill.films.R
import ru.myrkwill.films.databinding.ItemLayoutBinding
import ru.myrkwill.films.models.Movie

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var list = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)

        fun bind(item: Movie) = with(binding) {
            tvItemTitle.text = item.title
            tvItemDate.text = item.releaseDate
            Picasso.get()
                .load(item.posterPath)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageItem)
        }
    }
}