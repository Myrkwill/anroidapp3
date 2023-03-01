package ru.myrkwill.films.views.main

import android.util.Log
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

    fun setMovies(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

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
        private val binding = ItemLayoutBinding.bind(view)

        fun bind(item: Movie) = with(binding) {
            tvItemTitle.text = item.nameRu
            tvItemDate.text = item.year

            val url = "https://avatars.mds.yandex.net/get-kinopoisk-image/4774061/8ea16e5e-cbcb-41fa-90df-e1e7345caf4d/x1000"

            Picasso.get()
                .load(url)
                .resize(150, 200)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imageItem)
        }
    }
}