package ru.myrkwill.films.views.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.myrkwill.films.MAIN
import ru.myrkwill.films.R
import ru.myrkwill.films.databinding.FragmentMainBinding
import ru.myrkwill.films.models.Movie

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter
    private val viewModel: MainFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_favorite -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun init() = with(binding) {
        rvMain.layoutManager = LinearLayoutManager(activity)
        viewModel.getMovies()
        adapter = MainAdapter()
        rvMain.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                adapter.setMovies(it.films)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()

        fun clickMovie(movie: Movie) {
            val bundle = Bundle()
            bundle.putSerializable("movie", movie)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }
}