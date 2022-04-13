package com.kotlin.myapplication.apinew

import com.kotlin.myapplication.models.body.LoginRequest
import com.kotlin.myapplication.models.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by @erickrenata on 13/04/22.
 */

interface NewApiService {

    @POST("https://6124ba6d300c540017873d1e.mockapi.io/api/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}