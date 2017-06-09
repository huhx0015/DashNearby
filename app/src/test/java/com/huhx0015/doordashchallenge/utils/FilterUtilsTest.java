package com.huhx0015.doordashchallenge.utils;

import com.huhx0015.doordashchallenge.entities.FavoriteRestaurant;
import com.huhx0015.doordashchallenge.models.Restaurant;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class FilterUtilsTest {

    @Test
    public void testFilterRestaurantList() throws Exception {
        List<Restaurant> testRestaurantList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testRestaurantList.add(new Restaurant(i));
        }

        List<FavoriteRestaurant> testFavoriteList = new ArrayList<>();
        testFavoriteList.add(new FavoriteRestaurant(1));
        testFavoriteList.add(new FavoriteRestaurant(3));
        testFavoriteList.add(new FavoriteRestaurant(5));
        testFavoriteList.add(new FavoriteRestaurant(7));

        List<Restaurant> testExpectedList = new ArrayList<>();
        testExpectedList.add(new Restaurant(1));
        testExpectedList.add(new Restaurant(3));
        testExpectedList.add(new Restaurant(5));
        testExpectedList.add(new Restaurant(7));

        List<Restaurant> testResultList = FilterUtils.filterRestaurantList(testRestaurantList, testFavoriteList);

        for (int x = 0; x < testExpectedList.size(); x++) {
            int expectedId = testExpectedList.get(x).getId();
            int resultId = testResultList.get(x).getId();
            assertEquals(expectedId, resultId);
        }
    }
}
