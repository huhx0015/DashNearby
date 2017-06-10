package com.huhx0015.doordashchallenge.application;

import android.app.Application;
import com.huhx0015.doordashchallenge.constants.DashConstants;
import com.huhx0015.doordashchallenge.injections.components.DaggerNetworkComponent;
import com.huhx0015.doordashchallenge.injections.components.NetworkComponent;
import com.huhx0015.doordashchallenge.injections.modules.ApplicationModule;
import com.huhx0015.doordashchallenge.injections.modules.NetworkModule;
import com.orm.SugarContext;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class DashApplication extends Application {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // COMPONENT VARIABLES:
    private NetworkComponent mNetworkComponent;

    /** APPLICATION METHODS ____________________________________________________________________ **/

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        initLeakCanary();
        initNetworkComponent();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    /** INIT MEHODS ____________________________________________________________________________ **/

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initNetworkComponent() {
        mNetworkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(DashConstants.API_URL))
                .build();
    }

    /** GET METHODS ____________________________________________________________________________ **/

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}
