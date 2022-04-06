package com.kotlin.myapplication.features

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.myapplication.R
import com.kotlin.myapplication.adapters.MovieAdapter
import com.kotlin.myapplication.databinding.FragmentMovieListBinding
import com.kotlin.myapplication.models.item.MovieItemModel

/**
 * Created by @erickrenata on 05/04/22.
 */

class MovieListFragment : Fragment() {

    private val mActivity: MainActivity by lazy { context as MainActivity }

    private lateinit var binding: FragmentMovieListBinding
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    companion object {
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
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
            Toast.makeText(mActivity, "Fav Button Clicked", Toast.LENGTH_SHORT).show()
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