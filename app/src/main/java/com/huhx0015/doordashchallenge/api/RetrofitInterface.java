package com.huhx0015.doordashchallenge.api;

import com.huhx0015.doordashchallenge.models.Login;
import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.models.Token;
import com.huhx0015.doordashchallenge.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public interface RetrofitInterface {

    @POST("v2/auth/token/")
    Call<Token> getAuthToken(@Body Login login);

    @GET("/v2/consumer/me/")
    Call<User> getUser(@Header("Authorization") String token);

    @POST("/v2/auth/token/refresh/")
    Call<Token> refreshToken(@Header("Authorization") String token);

    @GET("v2/restaurant/")
    Call<List<Restaurant>> getRestaurantList(@Query("lat") String lat, @Query("lng") String lng);

    @GET("v2/restaurant/{restaurant_id}")
    Call<RestaurantDetail> getRestaurantDetail(@Path("restaurant_id") String restaurantId);
}