package com.xing.binge

import androidx.test.core.app.ActivityScenario
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.Before
import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.action.ViewActions.click
import org.junit.Test
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.Stage
import com.xing.binge.screens.SearchActivity
import com.xing.binge.util.CustomMatchers.Companion.withItemCount
import androidx.test.espresso.contrib.RecyclerViewActions
import com.xing.binge.util.CustomMatchers.Companion.withItemCountBiggerThan
import com.xing.binge.util.RecyclerViewMatcher.Companion.withRecyclerView

@RunWith(AndroidJUnit4::class)
class SearchActivityInstrumentationTest{
    /**
     * Use [ActivityScenario] to create and launch of the activity.
     */
    @Before
    fun launchActivity() {
        ActivityScenario.launch(SearchActivity::class.java)
    }

    @Test
    fun test_when_noSearchTextIsEntered_And_searchButtonIsClicked_Then_errorIsShown() {
        val expectedResult = getApplicationContext<Context>().getString(com.xing.binge.R.string.txt_edit_no_input)
        onView(withId(R.id.button_search)).perform(click())
        onView(withText(expectedResult)).inRoot(withDecorView(not(currentActivity?.window?.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_when_validSearchTextIsEntered_And_searchButtonIsClicked_Then_dataIsShown() {
        onView(withId(R.id.editText_search)).perform(typeText("action"))
        val expectedResult = getApplicationContext<Context>().getString(com.xing.binge.R.string.txt_edit_no_input)
        onView(withId(R.id.button_search)).perform(click())
        onView(withText(expectedResult)).inRoot(withDecorView(not(currentActivity?.window?.decorView)))
            .check(doesNotExist())
        onView(withId(R.id.recyclerView_movieList))
            .check(matches(withItemCount(20)))
    }

    @Test
    fun test_when_validSearchTextIsEntered_And_searchButtonIsClicked_And_scrollDownOccurs_Then_moreDataIsShown() {
        onView(withId(R.id.editText_search)).perform(typeText("action"))
        onView(withId(R.id.button_search)).perform(click())
        onView(withId(R.id.recyclerView_movieList))
            .check(matches(withItemCount(20)))
        onView(withId(R.id.recyclerView_movieList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        //TODO: could add idle resources here, sleep is bad practice, change!
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView_movieList))
            .check(matches(withItemCountBiggerThan(20)))
    }

    //TODO: test only valuable for MVP, not release as data will change when coming from server
    @Test
    fun test_when_pagingThroughResults_Then_starRatingChangesWithNewMedian() {
        onView(withId(R.id.editText_search)).perform(typeText("action"))
        onView(withId(R.id.button_search)).perform(click())
        onView(withRecyclerView(R.id.recyclerView_movieList)
            .atPositionOnView(0, R.id.imageView_movieListItem_star))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movieList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
            //TODO: could add idle resources here, sleep is bad practice, change!
        Thread.sleep(3000)
        onView(withRecyclerView(R.id.recyclerView_movieList)
            .atPositionOnView(0, R.id.imageView_movieListItem_star))
            .check(matches(not(isDisplayed())))
    }

    var currentActivity: Activity? = null
    fun getActivityInstance(): Activity? {
        getInstrumentation().runOnMainSync {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext())
                currentActivity = resumedActivities.iterator().next()
        }
        return currentActivity
    }
}