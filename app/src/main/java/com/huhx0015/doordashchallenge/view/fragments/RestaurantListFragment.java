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
import com.huhx0015.doordashchallenge.view.listeners.RestaurantListScrollListener;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.application.RestaurantApplication;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.databinding.FragmentRestaurantListBinding;
import com.huhx0015.doordashchallenge.entities.FavoriteRestaurant;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.utils.RestaurantUtils;
import com.huhx0015.doordashchallenge.view.adapters.RestaurantListAdapter;
import com.huhx0015.doordashchallenge.view.decorators.ListDividerItemDecoration;
import com.huhx0015.doordashchallenge.viewmodels.RestaurantListViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 *
 *  TODO: Sugar ORM is not compatible with Instant Run! Instant Run must be disabled first.
 *  SEE HERE: https://stackoverflow.com/questions/33031570/android-sugar-orm-no-such-table-exception
 */

public class RestaurantListFragment extends Fragment {

    private static final String LOG_TAG = RestaurantListFragment.class.getSimpleName();

    private static final String INSTANCE_RESTAURANT_LIST = LOG_TAG + "_INSTANCE_RESTAURANT_LIST";
    private static final String INSTANCE_TAG = LOG_TAG + "_INSTANCE_TAG";
    private static final String INSTANCE_LATITUDE = LOG_TAG + "_INSTANCE_LATITUDE";
    private static final String INSTANCE_LONGITUDE = LOG_TAG + "_INSTANCE_LONGITUDE";
    private static final String INSTANCE_END_OF_LIST = LOG_TAG + "_INSTANCE_END_OF_LIST";

    private static final int LIST_ITEM_LIMIT = 10;
    private static final String TAG_FAVORITES = "TAG_FAVORITES";

    private double mLatitude;
    private double mLongitude;

    private static final String QUERY_LAT = "lat";
    private static final String QUERY_LNG = "lng";
    private static final String QUERY_OFFSET = "offset";
    private static final String QUERY_LIMIT = "limit";

    private boolean mIsEndOfList = false;
    private FragmentRestaurantListBinding mBinding;
    private List<Restaurant> mRestaurantList;
    private RestaurantListAdapter mAdapter;
    private RestaurantListViewModel mViewModel;
    private String mTag;

    @Inject
    Retrofit mRetrofit;

    public static RestaurantListFragment newInstance(double lat, double lng, String tag) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        fragment.mLatitude = lat;
        fragment.mLongitude = lng;
        fragment.mTag = tag;
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
            mTag = savedInstanceState.getString(INSTANCE_TAG);
            mLatitude = savedInstanceState.getDouble(INSTANCE_LATITUDE);
            mLongitude = savedInstanceState.getDouble(INSTANCE_LONGITUDE);
            mRestaurantList = savedInstanceState.getParcelableArrayList(INSTANCE_RESTAURANT_LIST);
            mIsEndOfList = savedInstanceState.getBoolean(INSTANCE_END_OF_LIST);

            if (mRestaurantList != null && mRestaurantList.size() > 0) {
                setRecyclerView();
            } else {
                mIsEndOfList = false;
                queryRestaurantList(RestaurantConstants.DOORDASH_LAT, RestaurantConstants.DOORDASH_LNG,
                        0, LIST_ITEM_LIMIT);
            }
        } else {
            mIsEndOfList = false;
            queryRestaurantList(RestaurantConstants.DOORDASH_LAT, RestaurantConstants.DOORDASH_LNG,
                    0, LIST_ITEM_LIMIT);
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
        outState.putString(INSTANCE_TAG, mTag);
        outState.putBoolean(INSTANCE_END_OF_LIST, mIsEndOfList);
        outState.putDouble(INSTANCE_LATITUDE, mLatitude);
        outState.putDouble(INSTANCE_LONGITUDE, mLongitude);
        
        if (mRestaurantList != null) {
            outState.putParcelableArrayList(INSTANCE_RESTAURANT_LIST, new ArrayList<>(mRestaurantList));
        }
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
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setItemPrefetchEnabled(true);
        mLayoutManager.setInitialPrefetchItemCount(RestaurantConstants.RECYCLER_VIEW_PREFETCH);

        mBinding.fragmentRestaurantRecyclerView.setLayoutManager(mLayoutManager);
        mBinding.fragmentRestaurantRecyclerView.setHasFixedSize(true);
        mBinding.fragmentRestaurantRecyclerView.setDrawingCacheEnabled(true);
        mBinding.fragmentRestaurantRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mBinding.fragmentRestaurantRecyclerView.addItemDecoration(new ListDividerItemDecoration(getContext()));

        RestaurantListScrollListener mScrollListener = new RestaurantListScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!mTag.equals(TAG_FAVORITES) && !mIsEndOfList) {
                    queryRestaurantList(RestaurantConstants.DOORDASH_LAT, RestaurantConstants.DOORDASH_LNG,
                            current_page * LIST_ITEM_LIMIT, LIST_ITEM_LIMIT);
                }
            }
        };
        mBinding.fragmentRestaurantRecyclerView.addOnScrollListener(mScrollListener);

        setRecyclerView();
    }

    private void setRecyclerView() {
        mViewModel.setRestaurantListVisible(true);
        mAdapter = new RestaurantListAdapter(mRestaurantList, getContext());
        mAdapter.setHasStableIds(true);
        mBinding.fragmentRestaurantRecyclerView.setAdapter(mAdapter);
    }

    private void filterList() {
        List<FavoriteRestaurant> favoriteRestaurantList = FavoriteRestaurant.listAll(FavoriteRestaurant.class);
        if (favoriteRestaurantList != null && favoriteRestaurantList.size() > 0) {
            mRestaurantList = RestaurantUtils.filterRestaurantList(mRestaurantList, favoriteRestaurantList);
        } else {
            mRestaurantList.clear();
        }
    }

    private void updateList(List<Restaurant> updatedList, final int rangePosition) {
        mAdapter.updateRestaurantList(updatedList);
        mAdapter.notifyItemRangeInserted(rangePosition, mRestaurantList.size() - 1);
    }

    private void queryRestaurantList(Double lat, Double lng, final Integer offset, Integer limit) {
        RetrofitInterface restaurantListRequest = mRetrofit.create(RetrofitInterface.class);

        Map<String, String> requestParams = new HashMap<>();
        if (lat != null && lng != null) {
            requestParams.put(QUERY_LAT, String.valueOf(lat));
            requestParams.put(QUERY_LNG, String.valueOf(lng));
        }
        if (!mTag.equals(TAG_FAVORITES) && offset != null && limit != null) {
            requestParams.put(QUERY_OFFSET, String.valueOf(offset));
            requestParams.put(QUERY_LIMIT, String.valueOf(limit));
        }

        Call<List<Restaurant>> call = restaurantListRequest.getRestaurantList(requestParams);

        mViewModel.setProgressBarVisible(true);
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                mViewModel.setProgressBarVisible(false);

                Log.d(LOG_TAG, "onResponse(): Restaurant list query response received.");

                if (response.isSuccessful()) {

                    List<Restaurant> updatedRestaurantList = response.body();
                    if (updatedRestaurantList != null) {

                        Log.d(LOG_TAG, "onResponse(): Updated Restaurant list size: " + updatedRestaurantList.size());

                        if (mRestaurantList == null) {
                            mRestaurantList = new ArrayList<>();
                        }
                        mRestaurantList.addAll(updatedRestaurantList);

                        Log.d(LOG_TAG, "onResponse(): Restaurant list size: " + mRestaurantList.size());

                        // If returned list is less than the item limit, the last page of
                        // restaurants have been reached.
                        if (updatedRestaurantList.size() < LIST_ITEM_LIMIT - 1) {
                            mIsEndOfList = true;
                        }
                    }

                    // Filters the list if currently in "Favorites" view mode.
                    if (mTag.equals(TAG_FAVORITES)) {
                        filterList();
                    }

                    if (mRestaurantList != null && mRestaurantList.size() > 0) {
                        updateList(mRestaurantList, offset + 1);
                    } else {
                        mViewModel.setRestaurantListVisible(false);
                        mViewModel.setErrorVisibility(true);
                    }
                } else {
                    mViewModel.setRestaurantListVisible(false);
                    mViewModel.setErrorVisibility(true);
                }
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
