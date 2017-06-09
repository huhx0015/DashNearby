package com.huhx0015.doordashchallenge.view.listeners;

import com.huhx0015.doordashchallenge.models.RestaurantDetail;

/**
 * Created by Michael Yoon Huh on 6/8/2017.
 */

public interface RestaurantListAdapterListener {
    void onRestaurantClicked(int id, int position, String name, RestaurantDetail details);
}
