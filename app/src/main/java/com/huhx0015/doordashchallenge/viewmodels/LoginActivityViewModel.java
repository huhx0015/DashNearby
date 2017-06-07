package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class LoginActivityViewModel extends BaseObservable {

    private LoginActivityViewModelListener mListener;

    public void onClickLoginButton(View view) {
        if (mListener != null) {
            mListener.onLoginButtonClicked();
        }
    }

    public void setListener(LoginActivityViewModelListener listener) {
        this.mListener = listener;
    }

    public interface LoginActivityViewModelListener {
        void onLoginButtonClicked();
    }
}
