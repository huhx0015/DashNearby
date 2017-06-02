package com.huhx0015.doordashchallenge.viewmodels;

import android.databinding.BaseObservable;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 *
 * https://android.jlelse.eu/loading-images-with-data-binding-and-picasso-555dad683fdc
 */

public class RestaurantListAdapterViewModel extends BaseObservable {

    private String mImageUrl;
    private String mName;
    private String mTags;
    private String mStatus;

    public RestaurantListAdapterViewModel(String imageUrl, String name, String tags, String status) {
        this.mImageUrl = imageUrl;
        this.mName = name;
        this.mTags = tags;
        this.mStatus = status;
    }

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
}
