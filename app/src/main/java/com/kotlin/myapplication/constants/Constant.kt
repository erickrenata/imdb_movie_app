package com.kotlin.myapplication.constants


/**
 * Created by @erickrenata on 03/04/22.
 */

class Constant {

    companion object {
        const val LANGUAGE_EN_US = "en-US"
    }

}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}