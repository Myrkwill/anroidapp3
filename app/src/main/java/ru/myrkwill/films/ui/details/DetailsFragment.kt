package ru.myrkwill.films.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.myrkwill.films.data.moviedetails.MovieDetails
import ru.myrkwill.films.databinding.FragmentDetailsBinding
import ru.myrkwill.films.utils.Status
import ru.myrkwill.films.viewModel.MovieViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }
        
        if (args.imdbId == null) { return }
        viewModel.getMovieDetails(args.imdbId!!)
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            when (it.getContentIfNotHandled()?.status) {
                Status.SUCCESS -> {
                    binding.detailsProgress.visibility = View.GONE
                    it.peekContent().data?.let { movieDetails ->
                        setupMovieDetails(movieDetails)
                    }
                }
                Status.LOADING -> {
                    binding.detailsProgress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.detailsProgress.visibility = View.GONE
                }
                else -> {
                    binding.detailsProgress.visibility = View.GONE
                }
            }
        }
    }

    private fun setupMovieDetails(movieDetails: MovieDetails) = with(binding) {
        title.text = movieDetails.title
        movieTitle.text = movieDetails.title
        movieActors.text = "Actors: ${movieDetails.actors}"
        movieWriters.text = "Writers: ${movieDetails.writer}"
        movieRating.text = "Rating: ${movieDetails.imdbRating}"
        movieReleased.text = "Relesed: ${movieDetails.released}"
        movieVotes.text = "Votes: ${movieDetails.imdbVotes}"

        Glide.with(movieImage.context)
            .load(movieDetails.poster)
            .centerCrop()
            .into(movieImage)
    }

}