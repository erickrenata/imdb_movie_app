package com.kotlin.myapplication.features

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kotlin.myapplication.R
import com.kotlin.myapplication.constants.Status
import com.kotlin.myapplication.databinding.ActivityLoginBinding
import com.kotlin.myapplication.di.viewmodel.LoginViewModel
import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import com.kotlin.myapplication.models.mapper.setAllMoviesToFavorites
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by @erickrenata on 13/04/22.
 */

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setupObserve()
        binding.btnLogin.setOnClickListener {
            viewModel.callLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
    }


    private fun setupObserve() {
        viewModel.loginResult.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    // TODO loading
                }
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        navigateToMainScreen()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}