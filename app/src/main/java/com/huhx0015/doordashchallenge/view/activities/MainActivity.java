package com.huhx0015.doordashchallenge.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private static final String TAG_DISCOVER = "TAG_DISCOVER";
    private static final String TAG_FAVORITES = "TAG_FAVORITES";

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
            loadFragment(RestaurantListFragment.newInstance(TAG_DISCOVER), TAG_DISCOVER);
        }

        setToolbarTitle();
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

    private void initToolbar() {
        mActivityMainBinding.appBarMain.toolbar.setTitle("");
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
        fragmentTransaction.commit();
    }
}
