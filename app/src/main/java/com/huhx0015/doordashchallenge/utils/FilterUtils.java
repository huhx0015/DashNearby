package com.huhx0015.doordashchallenge.utils;

import com.huhx0015.doordashchallenge.entities.FavoriteRestaurant;
import com.huhx0015.doordashchallenge.models.Restaurant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class FilterUtils {

    /** FILTER METHODS ________________________________________________________________________ **/

    public static List<Restaurant> filterRestaurantList(List<Restaurant> restaurantList,
                                                        List<FavoriteRestaurant> favoriteList) {
        List<Restaurant> filteredList = null;

        int favoriteCount = favoriteList.size();
        for (int i = 0; i < restaurantList.size(); i++) {
            Restaurant restaurant = restaurantList.get(i);

            for (FavoriteRestaurant favoriteRestaurant : favoriteList) {
                if (restaurant.getId() == favoriteRestaurant.getRestaurantId()) {
                    if (filteredList == null) {
                        filteredList = new ArrayList<>();
                    }
                    filteredList.add(restaurant);
                    favoriteCount--;
                    break;
                }
            }

            if (favoriteCount == 0) {
                break;
            }
        }

        return filteredList;
    }
}
