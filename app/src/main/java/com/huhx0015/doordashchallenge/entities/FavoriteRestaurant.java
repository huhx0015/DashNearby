package com.huhx0015.doordashchallenge.entities;

import com.orm.SugarRecord;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class FavoriteRestaurant extends SugarRecord {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private String name;
    private int restaurantId;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public FavoriteRestaurant() {}

    public FavoriteRestaurant(int id) {
        this.restaurantId = id;
    }

    public FavoriteRestaurant(String name, int id) {
        this.restaurantId = id;
        this.name = name;
    }

    /** GET / SET METHODS ______________________________________________________________________ **/

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
