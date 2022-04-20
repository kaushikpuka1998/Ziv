package com.kgstrivers.ziv.Activities

import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.google.android.material.internal.ContextUtils.getActivity
import com.kgstrivers.ziv.R
import org.hamcrest.CoreMatchers.endsWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.nio.file.Files.exists
import java.util.regex.Pattern.matches


class MainActivityTest {



    @Rule
    @JvmField
    var rule  = ActivityTestRule(MainActivity::class.java)
    @Before
    fun setUp() {
    }


    @Test
    fun recyclerviewTest()
    {

        Thread.sleep(1500);
        Espresso.onView(withId(R.id.recycleview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9,click()))

        var r = ContentActivityTest()

        r.buttoncheck()

    }




}

