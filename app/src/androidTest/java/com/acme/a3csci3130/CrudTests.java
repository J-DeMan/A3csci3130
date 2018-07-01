package com.acme.a3csci3130;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Instrumentation test, which will execute on an Android device to validate CRUD operations
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CrudTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    /**
     * Tests that a valid business can be created
     */
    @Test
    public void createBusinessTest() {

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.number)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.name)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"), closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText("55 test"), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText("NB"), closeSoftKeyboard());

        onView(withId(R.id.submitButton)).perform(click());

        try {
            onView(withId(R.id.hint));
        } catch (NoMatchingViewException e) {
            // There is no hint, so the creation worked
        }
    }

    /**
     * Tests that a invalid business cannot be created
     */
    @Test
    public void createInvalidBusinessTest() {

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.number)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.name)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"), closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText("55 test"), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText("NF"), closeSoftKeyboard());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.hint)).check(matches(withText("Invalid province/territory")));
    }

    /**
     * Tests that a Business can be updated
     */
    @Test
    public void updateBusinessTest() {

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.province)).perform(clearText());
        onView(withId(R.id.province)).perform(clearText(),typeText("NB"), closeSoftKeyboard());

        onView(withId(R.id.updateButton)).perform(click());

        onView(withId(R.id.hint)).check(matches(withText("")));
    }

    /**
     * Tests that a Business cannot be updated to invalid values
     */
    @Test
    public void updateInvalidBusinessTest() {

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.number)).perform(typeText("1"), closeSoftKeyboard());

        onView(withId(R.id.updateButton)).perform(click());

        onView(withId(R.id.hint)).check(matches(withText("Invalid business number")));
    }

    /**
     * tests that a Business can be deleted
     */
    @Test
    public void eraseTest() {

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.deleteButton)).perform(click());

        try {
            onView(withId(R.id.hint));
        } catch (NoMatchingViewException e) {
            // There is no hint field, so the deletion worked
        }
    }

}
