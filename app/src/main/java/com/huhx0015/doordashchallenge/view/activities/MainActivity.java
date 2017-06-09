package com.huhx0015.doordashchallenge.view.activities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.databinding.ActivityMainBinding;
import com.huhx0015.doordashchallenge.databinding.AppBarMainBinding;
import com.huhx0015.doordashchallenge.databinding.ContentMainBinding;
import com.huhx0015.doordashchallenge.services.LocationService;
import com.huhx0015.doordashchallenge.utils.SnackbarUtils;
import com.huhx0015.doordashchallenge.view.fragments.RestaurantListFragment;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LocationService.LocationServiceListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String INSTANCE_FRAGMENT_TAG = LOG_TAG + "_INSTANCE_FRAGMENT_TAG";
    private static final String INSTANCE_LATITUDE = LOG_TAG + "_INSTANCE_LATITUDE";
    private static final String INSTANCE_LONGITUDE = LOG_TAG + "_INSTANCE_LONGITUDE";

    private static final String TAG_DISCOVER = "TAG_DISCOVER";
    private static final String TAG_FAVORITES = "TAG_FAVORITES";

    private Double mLatitude;
    private Double mLongitude;

    private boolean mIsLocationPermissionsChecked = false;
    private boolean mIsLocationPermissions = false;

    private ActivityMainBinding mActivityMainBinding;
    private AppBarMainBinding mAppBarMainBinding;
    private ContentMainBinding mContentMainBinding;

    private String mFragmentTag;

    private LocationService mLocationService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mFragmentTag = savedInstanceState.getString(INSTANCE_FRAGMENT_TAG);
            mLatitude = savedInstanceState.getDouble(INSTANCE_LATITUDE);
            mLongitude = savedInstanceState.getDouble(INSTANCE_LONGITUDE);
        }

        initBinding();
        initView();

        if (!mIsLocationPermissionsChecked) {
            initServices();
        }

        //setToolbarTitle();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContentMainBinding.unbind();
        mAppBarMainBinding.unbind();
        mActivityMainBinding.unbind();

        unbindService(mServiceConnection);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_FRAGMENT_TAG, mFragmentTag);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onBackPressed() {
        if (mActivityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_discover:
                if (!mFragmentTag.equals(TAG_DISCOVER)) {
                    loadFragment(RestaurantListFragment.newInstance(TAG_DISCOVER), TAG_DISCOVER);
                }
                break;
            case R.id.nav_favorites:
                if (!mFragmentTag.equals(TAG_FAVORITES)) {
                    loadFragment(RestaurantListFragment.newInstance(TAG_FAVORITES), TAG_FAVORITES);
                }
                break;
        }

        setToolbarTitle();
        mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initBinding() {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAppBarMainBinding = mActivityMainBinding.appBarMain;
        mContentMainBinding = mAppBarMainBinding.contentMain;
    }

    private void initView() {
        initToolbar();
        initDrawer();
        initNavigationView();
    }

    private void initServices() {
        MainActivityPermissionsDispatcher.initLocationServiceWithCheck(this);
    }

    private void initToolbar() {
        setSupportActionBar(mActivityMainBinding.appBarMain.toolbar);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mActivityMainBinding.drawerLayout, mAppBarMainBinding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        mActivityMainBinding.navView.setNavigationItemSelectedListener(this);
    }

    private void setToolbarTitle() {
        switch (mFragmentTag) {
            case TAG_DISCOVER:
                mAppBarMainBinding.toolbar.setTitle(R.string.title_discover);
                break;
            case TAG_FAVORITES:
                mAppBarMainBinding.toolbar.setTitle(R.string.title_favorites);
                break;
        }
    }

    private void loadFragment(Fragment fragment, String tag) {
        this.mFragmentTag = tag;

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
        if (existingFragment != null) {
            fragment = existingFragment;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(mContentMainBinding.mainFragmentContainer.getId(), fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService.LocationBinder binder = (LocationService.LocationBinder) service;
            mLocationService = binder.getService();
            mLocationService.setListener(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    /** LOCATION SERVICE LISTENER METHODS ______________________________________________________ **/

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @Override
    public void onLocationPermissionsRequested() {
        mLocationService.startLocationUpdates();
    }

    @Override
    public void onLocationUpdated(double lat, double lng) {
        this.mLatitude = lat;
        this.mLongitude = lng;

        loadFragment(RestaurantListFragment.newInstance(TAG_DISCOVER), TAG_DISCOVER);
    }

    @Override
    public void onLocationFailed() {
        SnackbarUtils.displaySnackbar(mActivityMainBinding.getRoot(),
                "Failed to get location update, using default coordinates.",
                Snackbar.LENGTH_SHORT, ContextCompat.getColor(this, R.color.colorAccent));
    }

    /** PERMISSIONS METHODS ____________________________________________________________________ **/

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    protected void initLocationService() {
        Intent intent = new Intent(this, LocationService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void handleDeniedLocationPermissions() {
        mIsLocationPermissionsChecked = true;

        SnackbarUtils.displaySnackbar(mActivityMainBinding.getRoot(),
                "Failed to get location update, using default coordinates.",
                Snackbar.LENGTH_SHORT, ContextCompat.getColor(this, R.color.colorAccent));

        loadFragment(RestaurantListFragment.newInstance(TAG_DISCOVER), TAG_DISCOVER);
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void handleNeverAskAgainLocationPermissions() {
        mIsLocationPermissionsChecked = true;

        SnackbarUtils.displaySnackbar(mActivityMainBinding.getRoot(),
                "Failed to get location update, using default coordinates.",
                Snackbar.LENGTH_SHORT, ContextCompat.getColor(this, R.color.colorAccent));

        loadFragment(RestaurantListFragment.newInstance(TAG_DISCOVER), TAG_DISCOVER);
    }
}
