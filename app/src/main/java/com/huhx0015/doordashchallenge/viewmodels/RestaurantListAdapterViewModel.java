package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListAdapterViewModel extends BaseObservable {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private RestaurantListAdapterViewModelListener mListener;

    private String mImageUrl;
    private String mName;
    private String mTags;
    private String mStatus;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public RestaurantListAdapterViewModel(String imageUrl, String name, String tags, String status) {
        this.mImageUrl = imageUrl;
        this.mName = name;
        this.mTags = tags;
        this.mStatus = status;
    }

    /** CLICK METHODS __________________________________________________________________________ **/

    public void onClickRow(View view) {
        if (mListener != null) {
            mListener.onRowClicked();
        }
    }

    /** GET / SET METHODS ______________________________________________________________________ **/

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getTags() {
        return mTags;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setListener(RestaurantListAdapterViewModelListener listener) {
        this.mListener = listener;
    }

    /** INTERFACE METHODS ______________________________________________________________________ **/

    public interface RestaurantListAdapterViewModelListener {
        void onRowClicked();
    }
}
