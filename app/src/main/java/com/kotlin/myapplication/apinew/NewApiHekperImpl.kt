package com.kotlin.myapplication.apinew

import com.kotlin.myapplication.models.body.LoginRequest
import com.kotlin.myapplication.models.response.LoginResponse
import com.kotlin.myapplication.models.response.MovieResponse
import retrofit2.Response


/**
 * Created by @erickrenata on 13/04/22.
 */

class NewApiHelperImpl(private val apiService: NewApiService) : NewApiHelper {

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> =
        apiService.login(loginRequest)


}