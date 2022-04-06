package com.kotlin.myapplication.utils

import com.kotlin.myapplication.utils.DateUtils.MMMM_DD_YYYY
import com.kotlin.myapplication.utils.DateUtils.YYYY_MM_DD
import org.junit.Assert
import org.junit.Test

/**
 * Created by @erickrenata on 06/04/22.
 */

class DateUtilsTest {

    @Test
    fun `formatDate empty date return -`() {
        val result = DateUtils.formatDate("", YYYY_MM_DD, MMMM_DD_YYYY)
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate null date return -`() {
        val result = DateUtils.formatDate(null, YYYY_MM_DD, MMMM_DD_YYYY)
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate date with empty old format date return -`() {
        val result = DateUtils.formatDate("2022-04-04", "", MMMM_DD_YYYY)
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate date with empty new format date return -`() {
        val result = DateUtils.formatDate("2022-04-04", YYYY_MM_DD, "")
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate date with wrong format date return -`() {
        val result = DateUtils.formatDate("abcdefgh", YYYY_MM_DD, MMMM_DD_YYYY)
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate date with incorrect old format date return -`() {
        val result = DateUtils.formatDate("2022-04-04", "abcdefgh", MMMM_DD_YYYY)
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate date with incorrect new format date return -`() {
        val result = DateUtils.formatDate("2022-04-04", YYYY_MM_DD, "abcdefgh")
        Assert.assertEquals(result, "-")
    }

    @Test
    fun `formatDate date with correct new format date and correct old format date return correct date`() {
        val result = DateUtils.formatDate("2022-04-04", YYYY_MM_DD, MMMM_DD_YYYY)
        Assert.assertEquals(result, "April 04, 2022")
    }
}