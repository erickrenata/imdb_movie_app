package com.kotlin.myapplication.models.body


/**
 * Created by @erickrenata on 13/04/22.
 */


data class LoginRequest(
    var username: String? = "",
    var password: String? = ""
)