package com.kotlin.myapplication.di.repository

import com.kotlin.myapplication.apinew.NewApiHelper
import com.kotlin.myapplication.models.body.LoginRequest


/**
 * Created by @erickrenata on 13/04/22.
 */

class LoginRepository(private val apiHelper: NewApiHelper) {

    suspend fun login(loginRequest: LoginRequest) = apiHelper.login(loginRequest)

}