package com.huhx0015.doordashchallenge.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class RestaurantPreferences {

    private static final String AUTH_TOKEN = "AUTH_TOKEN";

    public static void setAuthToken(String token, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(AUTH_TOKEN, token);
        preferencesEditor.apply();
    }

    public static String getAuthToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(AUTH_TOKEN, null);
    }
}
