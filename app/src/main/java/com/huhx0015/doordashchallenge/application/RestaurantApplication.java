package com.huhx0015.doordashchallenge.application;

import android.app.Application;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.injections.components.DaggerNetworkComponent;
import com.huhx0015.doordashchallenge.injections.components.NetworkComponent;
import com.huhx0015.doordashchallenge.injections.modules.ApplicationModule;
import com.huhx0015.doordashchallenge.injections.modules.NetworkModule;
import com.orm.SugarContext;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantApplication extends Application {

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);

        mNetworkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(RestaurantConstants.API_URL))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}
