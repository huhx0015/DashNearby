package com.huhx0015.doordashchallenge.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by Michael Yoon Huh on 6/8/2017.
 */

public class LocationUtils {

    /** LOCATION METHODS _______________________________________________________________________ **/

    // isLocationEnabled(): Determines if Location services is enabled on the current device.
    // Reference: https://stackoverflow.com/questions/10311834/how-to-check-if-location-services-are-enabled
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }
}
