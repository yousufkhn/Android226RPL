package com.example.a226complete

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a226complete.unit6.CalculatorActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CalculatorActivity::class.java)

    @Test
    fun testAddition() {
        // Type numbers
        onView(withId(R.id.num1)).perform(typeText("5"), closeSoftKeyboard())
        onView(withId(R.id.num2)).perform(typeText("7"), closeSoftKeyboard())

        // Click add button
        onView(withId(R.id.addBtn)).perform(click())

        // Check result
        onView(withId(R.id.result)).check(matches(withText("12")))
    }
}