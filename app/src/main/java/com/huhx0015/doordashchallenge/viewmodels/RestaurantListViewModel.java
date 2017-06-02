package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListViewModel extends BaseObservable {

    private boolean mProgressBarVisibility = false;

    public boolean getProgressBarVisible() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisible(boolean visible) {
        this.mProgressBarVisibility = visible;
        notifyChange();
    }
}