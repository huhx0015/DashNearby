package com.huhx0015.doordashchallenge.entities;

import com.orm.SugarRecord;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 *
 *  TODO: Sugar ORM is not compatible with Instant Run! Instant Run must be disabled first.
 *  SEE HERE: https://stackoverflow.com/questions/33031570/android-sugar-orm-no-such-table-exception
 */

public class FavoriteRestaurant extends SugarRecord {

    private String name;
    private int restaurantId;

    public FavoriteRestaurant() {}

    public FavoriteRestaurant(int id) {
        this.restaurantId = id;
    }

    public FavoriteRestaurant(String name, int id) {
        this.restaurantId = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
