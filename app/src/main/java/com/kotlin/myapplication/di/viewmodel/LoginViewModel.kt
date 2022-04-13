package com.kotlin.myapplication.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.myapplication.di.repository.LoginRepository
import com.kotlin.myapplication.models.body.LoginRequest
import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.response.LoginResponse
import com.kotlin.myapplication.utils.ext.filterEmpty
import com.kotlin.myapplication.utils.ext.handleError
import com.kotlin.myapplication.utils.ext.isValidEmailAndPassword
import com.kotlin.myapplication.utils.network.Resource
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


/**
 * Created by @erickrenata on 13/04/22.
 */

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel(), KoinComponent {

    val loginResult = MutableLiveData<Resource<LoginResponse>>()

    fun callLogin(email: String, password: String) = viewModelScope.launch {
        val loginRequest = LoginRequest(
            email,
            password
        )
        val validationEmailAndPassword = loginRequest.isValidEmailAndPassword()
        if (!validationEmailAndPassword.first){
            loginResult.postValue(
                Resource.error(
                    validationEmailAndPassword.second
                )
            )
            return@launch

        }
        loginResult.postValue(Resource.loading(true))
        repository.login(loginRequest).let {
            loginResult.postValue(Resource.loading(false))
            if (it.isSuccessful) {
                loginResult.postValue(Resource.success(it.body()))
            } else {
                loginResult.postValue(
                    Resource.error(
                        it.errorBody().handleError().filterEmpty()
                    )
                )
            }
        }
    }
}