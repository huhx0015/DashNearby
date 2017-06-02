package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListViewModel extends BaseObservable {

    private boolean mRestaurantListVisibility = false;
    private boolean mProgressBarVisibility = false;
    private boolean mErrorVisibility = false;

    public boolean getProgressBarVisible() {
        return mProgressBarVisibility;
    }

    public boolean getErrorVisible() {
        return mErrorVisibility;
    }

    public boolean getRestaurantListVisible() {
        return mRestaurantListVisibility;
    }

    public void setProgressBarVisible(boolean visible) {
        this.mProgressBarVisibility = visible;
        notifyChange();
    }

    public void setRestaurantListVisible(boolean visible) {
        this.mRestaurantListVisibility = visible;
        notifyChange();
    }

    public void setErrorVisibility(boolean visibile) {
        this.mErrorVisibility = visibile;
        notifyChange();
    }
}