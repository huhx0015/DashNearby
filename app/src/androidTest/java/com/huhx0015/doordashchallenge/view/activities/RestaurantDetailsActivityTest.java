package com.huhx0015.doordashchallenge.view.activities;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RestaurantDetailsActivityTest {

    @Rule
    public ActivityTestRule<RestaurantDetailsActivity> mActivityTestRule = new ActivityTestRule<>(RestaurantDetailsActivity.class);

    @Test
    public void restaurantDetailsActivityAddFavoritesTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withText("Add to Favorites"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Remove from Favorites"), isDisplayed()));
        appCompatButton2.perform(click());
    }
}
