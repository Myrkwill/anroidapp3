package ru.myrkwill.films.views.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import ru.myrkwill.films.R
import ru.myrkwill.films.databinding.FragmentDetailBinding
import ru.myrkwill.films.models.Movie

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailFragmentViewModel by activityViewModels()

    private var currentMovie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as Movie?
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        currentMovie?.let { it ->
            Picasso.get()
                .load(it.posterUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imgDetail)

            tvTitle.text = it.nameRu
            tvDate.text = it.year
            var description = "Rating: ${it.rating}"
            description += "\nShow in ${it.countries.joinToString { it.country} }"
            description += "\nGenres: ${it.genres.joinToString { it.genre } }"
            tvDescription.text = description
        }
    }

    companion object {
        @JvmStatic fun newInstance() = DetailFragment()
    }
}