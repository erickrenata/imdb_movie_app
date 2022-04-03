package com.kotlin.myapplication.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.kotlin.myapplication.BuildConfig
import com.kotlin.myapplication.R
import com.kotlin.myapplication.constant.Status
import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_wadidaw).text = BuildConfig.API_KEY
        initObserve()
        viewModel.callGetPopularMovieList()
    }

    private fun initObserve() {
        viewModel.loader.observe(this) {
        }
        viewModel.movieList.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        findViewById<TextView>(R.id.tv_wadidaw).text = response.results?.get(0)?.original_title
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "This is an error ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}