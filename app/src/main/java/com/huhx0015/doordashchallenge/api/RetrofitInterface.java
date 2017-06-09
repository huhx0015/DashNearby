package com.huhx0015.doordashchallenge.api;

import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.models.Restaurant;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public interface RetrofitInterface {

    @GET("v2/restaurant/")
    Call<List<Restaurant>> getRestaurantList(@QueryMap Map<String, String> params);

    @GET("v2/restaurant/{restaurant_id}")
    Call<RestaurantDetail> getRestaurantDetail(@Path("restaurant_id") String restaurantId);
}