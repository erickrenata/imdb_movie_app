package com.kotlin.myapplication.utils

import com.kotlin.myapplication.utils.ext.formatDate
import com.kotlin.myapplication.utils.ext.getDateObject


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
        val oldDate = date.getDateObject(oldFormat)
        return oldDate.formatDate(newFormat)
    }

}