package com.huhx0015.doordashchallenge.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.application.RestaurantApplication;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.databinding.FragmentRestaurantListBinding;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.view.adapters.RestaurantListAdapter;
import com.huhx0015.doordashchallenge.view.decorators.ListDividerItemDecoration;
import com.huhx0015.doordashchallenge.viewmodels.RestaurantListViewModel;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListFragment extends Fragment {

    private static final String LOG_TAG = RestaurantListFragment.class.getSimpleName();

    private static final String INSTANCE_RESTAURANT_LIST = LOG_TAG + "_INSTANCE_RESTAURANT_LIST";

    private FragmentRestaurantListBinding mBinding;
    private List<Restaurant> mRestaurantList;
    private RestaurantListViewModel mViewModel;

    @Inject
    Retrofit mRetrofit;

    public static RestaurantListFragment newInstance() {
        RestaurantListFragment fragment = new RestaurantListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((RestaurantApplication) getActivity().getApplication()).getNetworkComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initBinding();
        initView();

        if (savedInstanceState != null) {
            mRestaurantList = savedInstanceState.getParcelableArrayList(INSTANCE_RESTAURANT_LIST);

            if (mRestaurantList != null && mRestaurantList.size() > 0) {
                setRecyclerView();
            } else {
                queryRestaurantList();
            }
        } else {
            queryRestaurantList();
        }

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(INSTANCE_RESTAURANT_LIST, new ArrayList<>(mRestaurantList));
    }

    private void initBinding() {
        mBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_restaurant_list, null, false);
        mViewModel = new RestaurantListViewModel();
        mBinding.setViewModel(mViewModel);
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
        mBinding.fragmentRestaurantRecyclerView.addItemDecoration(new ListDividerItemDecoration(getContext()));
    }

    private void setRecyclerView() {
        mViewModel.setRestaurantListVisible(true);
        RestaurantListAdapter adapter = new RestaurantListAdapter(mRestaurantList, getContext());
        adapter.setHasStableIds(true);
        mBinding.fragmentRestaurantRecyclerView.setAdapter(adapter);
    }

    private void queryRestaurantList() {
        RetrofitInterface restaurantListRequest = mRetrofit.create(RetrofitInterface.class);
        Call<List<Restaurant>> call = restaurantListRequest.getRestaurantList(
                String.valueOf(RestaurantConstants.DOORDASH_LAT),
                String.valueOf(RestaurantConstants.DOORDASH_LNG));

        mViewModel.setProgressBarVisible(true);
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                mViewModel.setProgressBarVisible(false);
                mRestaurantList = response.body();

                if (mRestaurantList != null && mRestaurantList.size() > 0) {
                    setRecyclerView();
                } else {
                    mViewModel.setErrorVisibility(true);
                }

                Log.d(LOG_TAG, "onResponse(): Restaurant list query response received.");
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                mViewModel.setProgressBarVisible(false);
                mViewModel.setErrorVisibility(true);
                Log.e(LOG_TAG, "ERROR: onFailure(): Restaurant list query failed: " + t.getLocalizedMessage());
            }
        });
    }
}
