package com.huhx0015.doordashchallenge.api;

import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.models.RestaurantList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public interface RetrofitInterface {

    @GET("/restaurant/?lat={lat}&lng={lng}")
    Call<RestaurantList> getRestaurantList(@Query("lat") String lat, @Query("lng") String lng);

    @GET("/restaurant/{restaurant_id}")
    Call<RestaurantDetail> getRestaurantDetail(@Query("restaurant_id") String restaurantId);
}