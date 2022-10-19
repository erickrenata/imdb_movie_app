package com.kotlin.myapplication.features

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kotlin.myapplication.R
import com.kotlin.myapplication.constants.Constant
import com.kotlin.myapplication.constants.Status
import com.kotlin.myapplication.databinding.ActivityMovieDetailBinding
import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.mapper.setAllMoviesToFavorites
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by @erickrenata on 04/04/22.
 */

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val item =
            intent.extras?.getParcelable<MovieItemModel>(MovieItemModel::class.java.simpleName)

        setupToolbar()
        setupData(item)

        binding.fab.setOnClickListener {
            item?.let {
                onFavIconClicked(item)
            }
        }

        setupObserver()
        callApi(item?.id)
    }

    private fun setupObserver() {
        viewModel.trailerMovieList.observe(this) {
            when (it.status) {
                Status.LOADING -> {
//                    if (it.loading) showLoader() else hideLoader()
                }
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        Toast.makeText(this, (response.size.toString() + " "), Toast.LENGTH_SHORT).show()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "This is an error : ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        viewModel.reviewMovieList.observe(this) {
            when (it.status) {
                Status.LOADING -> {
//                    if (it.loading) showLoader() else hideLoader()
                }
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        Toast.makeText(this, (response.size.toString() + " "), Toast.LENGTH_SHORT).show()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "This is an error : ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun callApi(id: Int?) {
        viewModel.callGetTrailerMovieList(id.toString())
        viewModel.callGetReviewMovieList(id.toString())
    }

    private fun onFavIconClicked(item: MovieItemModel) {
        if (item.isLiked) {
            viewModel.deleteMovies(item)
            binding.fab.setImageResource(R.drawable.ic_fav_outline)
            item.isLiked = false
        } else {
            viewModel.saveMovie(item)
            binding.fab.setImageResource(R.drawable.ic_fav_filled)
            item.isLiked = true
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.navigationIcon?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_IN)
            } else {
                setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupData(item: MovieItemModel?) {
        binding.item = item
    }


}