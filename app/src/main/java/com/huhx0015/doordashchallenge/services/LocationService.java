package com.huhx0015.doordashchallenge.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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

    private static final String LOG_TAG = LocationService.class.getSimpleName();

    private static final int LOCATION_REQUEST_INTERVAL = 15000;

    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */

    private IBinder mBinder;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private boolean mIsProviderAvailable = false;

    private LocationServiceListener mListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder == null) {
            mBinder = new LocationBinder();
        }
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initGoogleClient();
        connectClient(true);
        return START_STICKY;
    }

    private void initGoogleClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }

    /** LOCATION METHODS _______________________________________________________________________ **/

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
    }

    public void startLocationUpdates() {
        if (mLocationRequest == null) {
            initLocationRequest();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);
        } else {
            mListener.onLocationPermissionsRequested();
        }
    }

    /** CLIENT METHODS _________________________________________________________________________ **/

    public void connectClient(boolean isConnect) {
        if (mGoogleApiClient != null) {

            if (isConnect) {
                mGoogleApiClient.connect();
            } else {
                mGoogleApiClient.disconnect();
            }
        }
    }

    /** SET METHODS ____________________________________________________________________________ **/

    public void setListener(LocationServiceListener listener) {
        this.mListener = listener;
    }

    /** GOOGLE API CLIENT CALLBACK METHODS _____________________________________________________ **/

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mListener.onLocationFailed();
    }

    /** LOCATION LISTENER METHODS ______________________________________________________________ **/

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            double latitiude = location.getLatitude();
            double longitude = location.getLongitude();

            Log.d(LOG_TAG, "onLocationChanged(): Latitude: " +latitiude + " | Longitude: " + longitude);

            mListener.onLocationUpdated(location.getLatitude(), location.getLongitude());
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
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
