package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantDetailsViewModel extends BaseObservable {

    private RestaurantDetailsViewModelListener mListener;

    private boolean mRestaurantDetailsVisibility = false;
    private boolean mProgressBarVisibility = false;
    private boolean mErrorVisibility = false;

    private String mImageUrl;
    private String mTags;
    private String mStatus;
    private String mRating;

    public RestaurantDetailsViewModel(RestaurantDetailsViewModelListener listener) {
        this.mListener = listener;
    }

    public void onClickAddFavorite(View view) {
        if (mListener != null) {
            mListener.onAddFavoriteClicked();
        }
    }

    public boolean getRestaurantDetailsVisible() {
        return mRestaurantDetailsVisibility;
    }

    public boolean getProgressBarVisible() {
        return mProgressBarVisibility;
    }

    public boolean getErrorVisible() {
        return mErrorVisibility;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTags() {
        return mTags;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getRating() {
        return mRating;
    }

    public void setRestaurantDetails(String image, String tags, String status, String rating) {
        this.mImageUrl = image;
        this.mTags = tags;
        this.mStatus = status;
        this.mRating = rating;
        notifyChange();
    }

    public void setProgressBarVisible(boolean visible) {
        this.mProgressBarVisibility = visible;
        notifyChange();
    }

    public void setRestaurantDetailsVisible(boolean visible) {
        this.mRestaurantDetailsVisibility = visible;
        notifyChange();
    }

    public void setErrorVisibility(boolean visibile) {
        this.mErrorVisibility = visibile;
        notifyChange();
    }

    public interface RestaurantDetailsViewModelListener {
        void onAddFavoriteClicked();
    }
}
