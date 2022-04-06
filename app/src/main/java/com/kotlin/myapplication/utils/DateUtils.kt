package com.kotlin.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by @erickrenata on 05/04/22.
 */

object DateUtils {

    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val MMMM_DD_YYYY = "MMMM dd, yyyy"

    fun formatDate(date: String?, oldFormat: String, newFormat: String): String {
        if (date == null)
            return "-"
        if (date.isEmpty())
            return "-"
        if (oldFormat.isEmpty())
            return "-"
        if (newFormat.isEmpty())
            return "-"
        val oldDate = try {
            SimpleDateFormat(oldFormat, Locale.US).parse(date)
        } catch (e: Exception) {
            return "-"
        }
        return try {
            SimpleDateFormat(newFormat, Locale.US).format(oldDate)
        } catch (e: Exception) {
            "-"
        }
    }

}