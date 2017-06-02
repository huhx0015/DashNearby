package com.huhx0015.doordashchallenge.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.databinding.FragmentRestaurantListBinding;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListFragment extends Fragment {

    private FragmentRestaurantListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_restaurant_list, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
