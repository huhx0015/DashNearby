package com.huhx0015.doordashchallenge.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.application.RestaurantApplication;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.databinding.FragmentRestaurantListBinding;
import javax.inject.Inject;
import retrofit2.Retrofit;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListFragment extends Fragment {

    private FragmentRestaurantListBinding mBinding;

    @Inject
    Retrofit mRetrofit;

    public static RestaurantListFragment newInstance() {
        RestaurantListFragment fragment = new RestaurantListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RestaurantApplication) getActivity().getApplication()).getNetworkComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_restaurant_list, null, false);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.unbind();
    }

    private void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setItemPrefetchEnabled(true);
        layoutManager.setInitialPrefetchItemCount(RestaurantConstants.RECYCLER_VIEW_PREFETCH);

        mBinding.fragmentRestaurantRecyclerView.setLayoutManager(layoutManager);
        mBinding.fragmentRestaurantRecyclerView.setHasFixedSize(true);
        mBinding.fragmentRestaurantRecyclerView.setDrawingCacheEnabled(true);
        mBinding.fragmentRestaurantRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        //RestaurantListAdapter adapter = new RestaurantListAdapter(null);
        //adapter.setHasStableIds(true);
        //mBinding.fragmentRestaurantRecyclerView.setAdapter(adapter);
    }
}
