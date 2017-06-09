package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class ContentMainViewModel extends BaseObservable {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private boolean mProgressBarVisibility = false;

    /** GET / SET METHODS ______________________________________________________________________ **/

    public boolean getProgressBarVisible() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisible(boolean visible) {
        this.mProgressBarVisibility = visible;
        notifyChange();
    }
}
