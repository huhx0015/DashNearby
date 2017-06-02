package com.huhx0015.doordashchallenge.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.AppCompatImageView;
import com.huhx0015.doordashchallenge.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class BindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(AppCompatImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
