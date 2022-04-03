package com.kotlin.myapplication.features

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.myapplication.R
import com.kotlin.myapplication.adapters.MovieAdapter
import com.kotlin.myapplication.constants.Status
import com.kotlin.myapplication.databinding.ActivityMainBinding
import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding : ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupRecyclerView()
        setupObserve()
        viewModel.callGetPopularMovieList()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovie.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        movieAdapter.setOnClickListener {
            Toast.makeText(this, "This title is ${it.title}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObserve() {
        viewModel.loader.observe(this) {
        }
        viewModel.movieList.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        movieAdapter.differ.submitList(response)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "This is an error : ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}