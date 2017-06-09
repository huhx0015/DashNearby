package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class LoginActivityViewModel extends BaseObservable {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private boolean mLoginFieldVisibility = false;
    private boolean mProgressBarVisibility = false;

    private LoginActivityViewModelListener mListener;

    public void onClickLoginButton(View view) {
        if (mListener != null) {
            mListener.onLoginButtonClicked();
        }
    }

    /** GET / SET METHODS ______________________________________________________________________ **/

    public boolean getLoginFieldVisibility() {
        return mLoginFieldVisibility;
    }

    public void setLoginFieldVisibility(boolean isVisible) {
        this.mLoginFieldVisibility = isVisible;
        notifyChange();
    }

    public boolean getProgressBarVisibility() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisibility(boolean isVisible) {
        this.mProgressBarVisibility = isVisible;
        notifyChange();
    }

    public void setListener(LoginActivityViewModelListener listener) {
        this.mListener = listener;
    }

    /** INTERFACE METHODS ______________________________________________________________________ **/

    public interface LoginActivityViewModelListener {
        void onLoginButtonClicked();
    }
}
