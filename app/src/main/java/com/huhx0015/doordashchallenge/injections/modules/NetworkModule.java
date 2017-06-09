package com.huhx0015.doordashchallenge.injections.modules;

import android.app.Application;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huhx0015.doordashchallenge.constants.DashConstants;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

@Module
public class NetworkModule {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private String mBaseUrl;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public NetworkModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    /** MODULE METHODS _________________________________________________________________________ **/

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        return new Cache(application.getCacheDir(), DashConstants.HTTP_CLIENT_CACHE);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(client)
                .build();
    }
}