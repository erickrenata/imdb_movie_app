package com.kotlin.myapplication.utils.ext

import com.kotlin.myapplication.utils.ext.filterEmpty
import org.junit.Assert
import org.junit.Test


/**
 * Created by @erickrenata on 06/04/22.
 */

class StringExtTest {

    @Test
    fun `filterEmpty empty string return empty`(){
        Assert.assertEquals("".filterEmpty(), "")
    }

    @Test
    fun `filterEmpty null return empty`(){
        Assert.assertEquals(null.filterEmpty(), "")
    }

    @Test
    fun `filterEmpty string return string`(){
        Assert.assertEquals("text".filterEmpty(), "text")
    }



}