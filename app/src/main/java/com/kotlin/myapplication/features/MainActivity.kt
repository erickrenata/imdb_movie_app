package com.kotlin.myapplication.features

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.myapplication.R
import com.kotlin.myapplication.constants.Status
import com.kotlin.myapplication.databinding.ActivityMainBinding
import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val popularFragment: MovieListFragment by lazy { MovieListFragment.newInstance() }
    private val topRatedFragment: MovieListFragment by lazy { MovieListFragment.newInstance() }
    private val favoritesFragment: MovieListFragment by lazy { MovieListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupTabLayout()
        setupObserve()

        viewModel.callGetPopularMovieList()
        viewModel.callGetTopRatedMovieList()
    }

    private fun setupTabLayout() {
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        binding.viewPager.adapter = AdapterTabPager()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = getString(R.string.label_popular)
                1 -> tab.text = getString(R.string.label_top_rated)
                2 -> tab.text = getString(R.string.label_favorites)
            }
        }.attach()
    }

    private fun createBadgeOnFavouritesTab() {
        binding.tabLayout.getTabAt(2)?.apply {
            // TODO : will update once favorite feature completed
            orCreateBadge.number = 10
            badge?.isVisible = true
        }
    }

    private fun setupObserve() {
        viewModel.popularMovieList.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    if (it.loading) Log.d("POPULAR", "now loading") else Log.d("POPULAR", "loading completed")
                }
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        popularFragment.setDataList(response)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "This is an error : ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        viewModel.topRatedMovieList.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    if (it.loading) Log.d("TOP RATED", "now loading") else Log.d("TOP RATED", "loading completed")
                }
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        topRatedFragment.setDataList(response)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "This is an error : ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    inner class AdapterTabPager : FragmentStateAdapter(supportFragmentManager, lifecycle) {

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    popularFragment
                }
                1 -> {
                    topRatedFragment
                }
                2 -> {
                    favoritesFragment
                }
                else -> MovieListFragment.newInstance()
            }
        }
    }
}