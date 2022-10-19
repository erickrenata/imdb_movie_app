package com.kotlin.myapplication.utils.network

import com.kotlin.myapplication.constants.ErrorCondition
import com.kotlin.myapplication.constants.Status


/**
 * Created by @erickrenata on 03/04/22.
 */


data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = "",
    val errorCode: ErrorCondition = ErrorCondition.NETWORKISSUE,
    val loading: Boolean = false
) {

    companion object {

        fun <T> loading(data: Boolean?): Resource<T> {
            return Resource(Status.LOADING, loading = data == true)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null, errorCode: ErrorCondition = ErrorCondition.NETWORKISSUE): Resource<T> {
            return Resource(Status.ERROR, data, msg, errorCode = errorCode)
        }

    }

}