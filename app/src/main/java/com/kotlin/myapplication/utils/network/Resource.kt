package com.kotlin.myapplication.utils.network

import com.kotlin.myapplication.constant.Status


/**
 * Created by @erickrenata on 03/04/22.
 */


data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = "",
    val errorCode: Int = 0
) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null, errorCode: Int = 0): Resource<T> {
            return Resource(Status.ERROR, data, msg, errorCode = errorCode)
        }

    }

}