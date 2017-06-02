package com.huhx0015.doordashchallenge.injections.modules;

import android.app.Application;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}