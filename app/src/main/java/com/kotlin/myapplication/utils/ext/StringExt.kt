package com.kotlin.myapplication.utils.ext

import android.text.TextUtils


/**
 * Created by @erickrenata on 03/04/22.
 */


fun String?.filterEmpty(): String {
    if (this.isNullOrEmpty())
        return ""
    return this
}