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
import android.util.Log;
import android.view.MenuItem;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.constants.DashConstants;
import com.huhx0015.doordashchallenge.databinding.ActivityMainBinding;
import com.huhx0015.doordashchallenge.databinding.AppBarMainBinding;
import com.huhx0015.doordashchallenge.databinding.ContentMainBinding;
import com.huhx0015.doordashchallenge.services.LocationService;
import com.huhx0015.doordashchallenge.utils.LocationUtils;
import com.huhx0015.doordashchallenge.utils.SnackbarUtils;
import com.huhx0015.doordashchallenge.view.fragments.RestaurantListFragment;
import com.huhx0015.doordashchallenge.viewmodels.ContentMainViewModel;
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

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // ACTIVITY VARIABLES:
    private boolean mIsActivityPaused = false;

    // DATABINDING VARIABLES:
    private ActivityMainBinding mActivityMainBinding;
    private AppBarMainBinding mAppBarMainBinding;
    private ContentMainBinding mContentMainBinding;
    private ContentMainViewModel mContentMainViewModel;

    // LOCATION VARIABLES:
    private Double mLatitude;
    private Double mLongitude;
    private boolean mIsLocationPermissionsAsked = false;

    // LOGGING VARIABLES:
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // INSTANCE VARIABLES:
    private static final String INSTANCE_FRAGMENT_TAG = LOG_TAG + "_INSTANCE_FRAGMENT_TAG";
    private static final String INSTANCE_LATITUDE = LOG_TAG + "_INSTANCE_LATITUDE";
    private static final String INSTANCE_LONGITUDE = LOG_TAG + "_INSTANCE_LONGITUDE";
    private static final String INSTANCE_IS_LOCATION_PERMISSIONS_ASKED = LOG_TAG + "_INSTANCE_IS_LOCATION_PERMISSIONS_ASKED";

    // SERVICE VARIABLES:
    private LocationService mLocationService;
    private boolean mServiceBound = false;

    // TAG VARIABLES:
    private String mFragmentTag;

    /** ACTIVITY LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();

        if (savedInstanceState != null) {
            mFragmentTag = savedInstanceState.getString(INSTANCE_FRAGMENT_TAG, null);
            mLatitude = savedInstanceState.getDouble(INSTANCE_LATITUDE, DashConstants.DOORDASH_LAT);
            mLongitude = savedInstanceState.getDouble(INSTANCE_LONGITUDE, DashConstants.DOORDASH_LNG);
            mIsLocationPermissionsAsked = savedInstanceState.getBoolean(INSTANCE_IS_LOCATION_PERMISSIONS_ASKED);
        }

        initView();

        if (!mIsLocationPermissionsAsked || mFragmentTag == null) {
            initServices();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
        checkCoordindatesReady();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityPaused = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContentMainBinding.unbind();
        mAppBarMainBinding.unbind();
        mActivityMainBinding.unbind();

        if (mServiceBound) {
            unbindService(mServiceConnection);
        }
    }

    /** ACTIVITY EXTENSION METHODS _____________________________________________________________ **/

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

        if (mFragmentTag != null) {
            switch (id) {
                case R.id.nav_discover:
                    if (!mFragmentTag.equals(DashConstants.TAG_DISCOVER)) {
                        loadFragment(RestaurantListFragment.newInstance(mLatitude, mLongitude, DashConstants.TAG_DISCOVER), DashConstants.TAG_DISCOVER);
                    }
                    break;
                case R.id.nav_favorites:
                    if (!mFragmentTag.equals(DashConstants.TAG_FAVORITES)) {
                        loadFragment(RestaurantListFragment.newInstance(mLatitude, mLongitude, DashConstants.TAG_FAVORITES), DashConstants.TAG_FAVORITES);
                    }
                    break;
            }
        }

        mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(INSTANCE_IS_LOCATION_PERMISSIONS_ASKED, mIsLocationPermissionsAsked);

        if (mFragmentTag != null) {
            outState.putString(INSTANCE_FRAGMENT_TAG, mFragmentTag);
        }
        if (mLatitude != null) {
            outState.putDouble(INSTANCE_LATITUDE, mLatitude);
        }
        if (mLongitude != null) {
            outState.putDouble(INSTANCE_LONGITUDE, mLongitude);
        }
    }

    /** INIT METHODS ___________________________________________________________________________ **/
    
    private void initBinding() {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAppBarMainBinding = mActivityMainBinding.appBarMain;
        mContentMainBinding = mAppBarMainBinding.contentMain;
        mContentMainViewModel = new ContentMainViewModel();
        mContentMainBinding.setViewModel(mContentMainViewModel);
    }

    private void initView() {
        initToolbar();
        initDrawer();
        initNavigationView();
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

    private void initToolbar() {
        mAppBarMainBinding.toolbar.setTitle(getToolbarTitle());
        setSupportActionBar(mActivityMainBinding.appBarMain.toolbar);
    }

    private void initServices() {
        MainActivityPermissionsDispatcher.initLocationServiceWithCheck(this);
    }

    private String getToolbarTitle() {
        switch (mFragmentTag != null ? mFragmentTag : "") {
            case DashConstants.TAG_DISCOVER:
                return getString(R.string.title_discover);
            case DashConstants.TAG_FAVORITES:
                return getString(R.string.title_favorites);
            default:
                return getString(R.string.app_name);
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

        mAppBarMainBinding.toolbar.setTitle(getToolbarTitle());
    }

    private void loadDefaultCoordinates(String message) {
        mIsLocationPermissionsAsked = true;
        mLatitude = DashConstants.DOORDASH_LAT;
        mLongitude = DashConstants.DOORDASH_LNG;

        if (!mIsActivityPaused) {
            mContentMainViewModel.setProgressBarVisible(false);
            SnackbarUtils.displaySnackbar(mActivityMainBinding.getRoot(), message,
                    Snackbar.LENGTH_SHORT, ContextCompat.getColor(this, R.color.colorAccent));
            loadFragment(RestaurantListFragment.newInstance(mLatitude, mLongitude, DashConstants.TAG_DISCOVER), DashConstants.TAG_DISCOVER);
        }
    }

    private void checkCoordindatesReady() {
        if (mFragmentTag == null) {
            if (mLatitude != null && mLongitude != null && mContentMainViewModel.getProgressBarVisible()) {
                mContentMainViewModel.setProgressBarVisible(false);
                loadFragment(RestaurantListFragment.newInstance(mLatitude, mLongitude, DashConstants.TAG_DISCOVER), DashConstants.TAG_DISCOVER);
            } else {
                mContentMainViewModel.setProgressBarVisible(true);
            }
        }
    }
    
    /** SERVICE CONNECTION _____________________________________________________________________ **/

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG, "onServiceConnected(): Service connected.");

            LocationService.LocationBinder binder = (LocationService.LocationBinder) service;
            mLocationService = binder.getService();
            mLocationService.setListener(MainActivity.this);
            mLocationService.connect(true);
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(LOG_TAG, "onServiceDisconnected(): Service disconnected.");
            mServiceBound = false;
        }
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

        if (!mIsActivityPaused) {
            mContentMainViewModel.setProgressBarVisible(false);
            loadFragment(RestaurantListFragment.newInstance(mLatitude, mLongitude, DashConstants.TAG_DISCOVER), DashConstants.TAG_DISCOVER);
        }
    }

    @Override
    public void onLocationFailed() {
        loadDefaultCoordinates(getString(R.string.location_update_failed));
    }

    /** PERMISSIONS METHODS ____________________________________________________________________ **/

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    protected void initLocationService() {
        if (LocationUtils.isLocationEnabled(this)) {
            mContentMainViewModel.setProgressBarVisible(true);
            mIsLocationPermissionsAsked = true;

            Intent intent = new Intent(this, LocationService.class);
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

            Log.d(LOG_TAG, "initLocationServices(): Starting location services...");
        } else {
            loadDefaultCoordinates(getString(R.string.location_disabled));
        }
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void handleDeniedLocationPermissions() {
        loadDefaultCoordinates(getString(R.string.location_permissions_denied));
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void handleNeverAskAgainLocationPermissions() {
        loadDefaultCoordinates(getString(R.string.location_permissions_unavailable));
    }
}
