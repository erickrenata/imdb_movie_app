package com.kotlin.myapplication.features

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kotlin.myapplication.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by @erickrenata on 07/04/22.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {


//    @get : Rule
//    var mActivityRule = ActivityScenarioRule(MovieDetailActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun clickForAddData() {
        onView(withId(R.id.fab)).perform(click())
    }

    @After
    fun tearDown() {
        //clean up code
    }
}