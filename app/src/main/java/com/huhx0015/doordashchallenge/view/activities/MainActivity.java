package com.huhx0015.doordashchallenge.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.databinding.ActivityMainBinding;
import com.huhx0015.doordashchallenge.databinding.AppBarMainBinding;
import com.huhx0015.doordashchallenge.databinding.ContentMainBinding;
import com.huhx0015.doordashchallenge.view.fragments.RestaurantListFragment;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String INSTANCE_FRAGMENT_TAG = LOG_TAG + "_INSTANCE_FRAGMENT_TAG";

    private ActivityMainBinding mActivityMainBinding;
    private AppBarMainBinding mAppBarMainBinding;
    private ContentMainBinding mContentMainBinding;

    private String mFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();

        if (savedInstanceState != null) {
            mFragmentTag = savedInstanceState.getString(INSTANCE_FRAGMENT_TAG);
        } else {
            loadFragment(RestaurantListFragment.newInstance(), RestaurantListFragment.class.getSimpleName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContentMainBinding.unbind();
        mAppBarMainBinding.unbind();
        mActivityMainBinding.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_FRAGMENT_TAG, mFragmentTag);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

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

    private void loadFragment(Fragment fragment, String tag) {
        this.mFragmentTag = tag;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(mContentMainBinding.mainFragmentContainer.getId(), fragment);
        fragmentTransaction.commit();
    }
}
