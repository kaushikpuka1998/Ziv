package com.kgstrivers.ziv.Activities

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.kgstrivers.ziv.R
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContentActivityTest {



    @Rule
    @JvmField
    var rule  = ActivityTestRule(ContentActivity::class.java)
    @Before
    fun setUp() {
    }


    @Test
    fun buttoncheck()
    {
        Espresso.onView(ViewMatchers.withId(R.id.addtocart)).perform(ViewActions.click());
    }


    @After
    fun tearDown() {
    }
}