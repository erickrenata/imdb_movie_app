package com.kotlin.myapplication.utils.ext

import android.text.TextUtils
import com.kotlin.myapplication.models.body.LoginRequest


/**
 * Created by @erickrenata on 03/04/22.
 */


fun String?.filterEmpty(): String {
    if (this.isNullOrEmpty())
        return ""
    return this
}

fun String?.isEmailCorrect(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this.toString()).matches()
}

fun LoginRequest?.isValidEmailAndPassword(): Pair<Boolean, String> {
    if (this == null) return Pair(false, "")
    if (username?.isEmpty() == true) {
        return Pair(false, "Empty email")
    }
    if (!username.isEmailCorrect()) {
        return Pair(false, "Invalid email format")
    }
    if (password?.isEmpty() == true) {
        return Pair(false, "Password empty")
    }
    if (password?.length!! < 4) {
        return Pair(false, "Password harus lebih dari atau sama dengan 4 digit")
    }
    return Pair(true, "")

}