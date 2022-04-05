package com.kotlin.myapplication.utils.ext

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by @erickrenata on 05/04/22.
 */


fun String.getDateObject(format: String): Date {
    return format.getSimpleDateFormat().parse(this)
}


fun String.getSimpleDateFormat(): SimpleDateFormat {
    return SimpleDateFormat(this)
}

fun Date.formatDate(newFormat: String): String {
    return try {
        newFormat.getSimpleDateFormat().format(this)
    } catch (e: Exception) {
        "-"
    }
}