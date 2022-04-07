package com.kotlin.myapplication.features

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.myapplication.R
import com.kotlin.myapplication.adapters.MovieAdapter
import com.kotlin.myapplication.constants.Constant.Companion.MOVIE_FAVORITES
import com.kotlin.myapplication.databinding.FragmentMovieListBinding
import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.mapper.setAllMoviesToFavorites
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by @erickrenata on 05/04/22.
 */

class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private val mActivity: MainActivity by lazy { context as MainActivity }

    private lateinit var binding: FragmentMovieListBinding
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    private lateinit var movieType: String

    companion object {
        fun newInstance(type: String): MovieListFragment {
            val movieListFragment = MovieListFragment()
            movieListFragment.movieType = type
            return movieListFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObserveSavedMovies()
    }

    fun showLoader(){
        binding.rvMovie.visibility = View.GONE
        binding.shimmerLoader.visibility = View.VISIBLE
        binding.shimmerLoader.startShimmerAnimation()
    }

    fun hideLoader(){
        binding.rvMovie.visibility = View.VISIBLE
        binding.shimmerLoader.visibility = View.GONE
        binding.shimmerLoader.stopShimmerAnimation()
    }

    private fun setupObserveSavedMovies() {
        viewModel.getSavedMovies().observe(viewLifecycleOwner) { movies ->
            if (movieType != MOVIE_FAVORITES) {
                movies.setAllMoviesToFavorites()
                for (i in 0 until movieAdapter.differ.currentList.size) {
                    val favoriteMovie = viewModel.getFavoriteMovieByID(movieAdapter.differ.currentList[i].id, movies)
                    if (favoriteMovie != null) {
                        if (movieAdapter.differ.currentList[i].isLiked != favoriteMovie.isLiked) {
                            movieAdapter.differ.currentList[i].isLiked = favoriteMovie.isLiked
                            movieAdapter.notifyItemChanged(i)
                        }
                    } else {
                        if (movieAdapter.differ.currentList[i].isLiked) {
                            movieAdapter.differ.currentList[i].isLiked =
                                !movieAdapter.differ.currentList[i].isLiked
                            movieAdapter.notifyItemChanged(i)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvMovie.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(mActivity, 2)
        }
        movieAdapter.setOnClickListener {
            navigateToDetailMovie(it)
        }
        movieAdapter.setOnFavIconClickedListener {
            updateFavoriteMovieList(it)
        }
    }

    private fun updateFavoriteMovieList(it: Int) {
        if (movieAdapter.differ.currentList[it].isLiked) {
            viewModel.deleteMovies(movieAdapter.differ.currentList[it])
        } else {
            viewModel.saveMovie(movieAdapter.differ.currentList[it])
        }
    }

    private fun navigateToDetailMovie(movie: MovieItemModel) {
        val bundle = Bundle()
        bundle.putParcelable(MovieItemModel::class.java.simpleName, movie)
        startActivity(
            Intent(mActivity, MovieDetailActivity::class.java)
                .putExtras(bundle)
        )
    }

    fun setDataList(list: List<MovieItemModel>) {
        movieAdapter.differ.submitList(list)
    }
}