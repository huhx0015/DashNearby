package com.huhx0015.doordashchallenge.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.huhx0015.doordashchallenge.R;

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
    private String mFee;
    private String mFavoriteButtonText;

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

    public String getFee() {
        return mFee;
    }

    public String getFavoriteButtonText() {
        return mFavoriteButtonText;
    }

    public void setRestaurantDetails(String image, String tags, String status, String fee) {
        this.mImageUrl = image;
        this.mTags = tags;
        this.mStatus = status;
        this.mFee = fee;
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

    public void setIsFavorite(boolean isFavorite, Context context) {
        if (isFavorite) {
            mFavoriteButtonText = context.getResources().getString(R.string.restaurant_details_remove_button);
        } else {
            mFavoriteButtonText = context.getResources().getString(R.string.restaurant_details_add_button);
        }
        notifyChange();
    }

    public interface RestaurantDetailsViewModelListener {
        void onAddFavoriteClicked();
    }
}
