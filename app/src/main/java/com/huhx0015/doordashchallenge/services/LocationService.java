package com.huhx0015.doordashchallenge.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Michael Yoon Huh on 6/8/2017.
 */

public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    /** CLASS VARIABLES _________________________________________________________________________**/

    // CONSTANT VARIABLES:
    private static final int UPDATE_INTERVAL = 10000;
    private static final int FASTEST_INTERVAL = 5000;

    // GOOGLE API VARIABLES:
    private GoogleApiClient mGoogleApiClient;

    // LOGGING VARIABLES:
    private static final String LOG_TAG = LocationService.class.getSimpleName();

    // LOCATION VARIABLES:
    private LocationRequest mLocationRequest;
    private LocationServiceListener mListener;
    private boolean mIsLocationFound = false;

    // SERVICE VARIABLES:
    private IBinder mBinder;

    // THREAD VARIABLES:
    private Handler mHandler;

    /** SERVICE METHODS ________________________________________________________________________ **/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder == null) {
            mBinder = new LocationBinder();
        }
        return mBinder;
    }

    /** LOCATION METHODS _______________________________________________________________________ **/

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setExpirationDuration(UPDATE_INTERVAL);
    }

    public void startLocationUpdates() {
        if (mLocationRequest == null) {
            initLocationRequest();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            if (mGoogleApiClient.isConnected()) {

                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (lastLocation != null) {
                    updateLocation(lastLocation);
                } else {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                            mLocationRequest, this);
                    startIntervalThread();
                }
            } else if (mListener != null) {
                mListener.onLocationFailed();
            }
        } else if (mListener != null) {
            mListener.onLocationPermissionsRequested();
        }
    }

    private void updateLocation(Location location) {
        double latitiude = location.getLatitude();
        double longitude = location.getLongitude();

        Log.d(LOG_TAG, "updateLocation(): Latitude: " +latitiude + " | Longitude: " + longitude);

        if (mListener != null) {
            mIsLocationFound = true;
            mListener.onLocationUpdated(location.getLatitude(), location.getLongitude());
        }
    }

    /** GOOGLE API CLIENT METHODS ______________________________________________________________ **/

    private void initGoogleClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }

    public void connect(boolean isConnect) {
        if (mGoogleApiClient == null) {
            initGoogleClient();
        }

        if (isConnect && !mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        } else if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /** THREAD METHODS _________________________________________________________________________ **/

    private void startIntervalThread() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(mThread, UPDATE_INTERVAL);
    }

    private Runnable mThread = new Runnable() {
        @Override
        public void run() {
            if (!mIsLocationFound && mListener != null) {
                mListener.onLocationFailed();
            }
        }
    };

    /** SET METHODS ____________________________________________________________________________ **/

    public void setListener(LocationServiceListener listener) {
        this.mListener = listener;
    }

    /** GOOGLE API CLIENT CALLBACK METHODS _____________________________________________________ **/

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(LOG_TAG, "onConnected(): Google API client connection established.");
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(LOG_TAG, "onConnectionSuspended(): Google API client connection suspended.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(LOG_TAG, "onConnectionFailed(): Google API client connection suspended.");

        if (mListener != null) {
            mIsLocationFound = true;
            mListener.onLocationFailed();
        }
    }

    /** LOCATION LISTENER METHODS ______________________________________________________________ **/

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            updateLocation(location);
        } else if (mListener != null) {
            mListener.onLocationFailed();
        }
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    /** INTERFACE ______________________________________________________________________________ **/

    public interface LocationServiceListener {
        void onLocationPermissionsRequested();
        void onLocationUpdated(double lat, double lng);
        void onLocationFailed();
    }

    /** SUBCLASSES _____________________________________________________________________________ **/

    public class LocationBinder extends Binder {

        public LocationService getService() {
            return LocationService.this;
        }
    }
}
