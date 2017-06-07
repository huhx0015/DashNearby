package com.huhx0015.doordashchallenge.injections.components;

import com.huhx0015.doordashchallenge.injections.modules.ApplicationModule;
import com.huhx0015.doordashchallenge.injections.modules.NetworkModule;
import com.huhx0015.doordashchallenge.models.Login;
import com.huhx0015.doordashchallenge.view.activities.LoginActivity;
import com.huhx0015.doordashchallenge.view.activities.RestaurantDetailsActivity;
import com.huhx0015.doordashchallenge.view.fragments.RestaurantListFragment;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

@Singleton
@Component(modules={ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(RestaurantListFragment fragment);
    void inject(RestaurantDetailsActivity activity);
    void inject(LoginActivity activity);
}