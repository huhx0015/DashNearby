package com.huhx0015.doordashchallenge.view.fragments;

import android.content.Intent;
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
import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.view.activities.RestaurantDetailsActivity;
import com.huhx0015.doordashchallenge.view.listeners.RestaurantListAdapterListener;
import com.huhx0015.doordashchallenge.view.listeners.RestaurantListScrollListener;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.application.DashApplication;
import com.huhx0015.doordashchallenge.constants.DashConstants;
import com.huhx0015.doordashchallenge.databinding.FragmentRestaurantListBinding;
import com.huhx0015.doordashchallenge.entities.FavoriteRestaurant;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.utils.FilterUtils;
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
 */

public class RestaurantListFragment extends Fragment implements RestaurantListAdapterListener {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // DATABINDING VARIABLES:
    private FragmentRestaurantListBinding mBinding;
    private RestaurantListViewModel mViewModel;

    // FRAGMENT VARIABLES:
    private RestaurantListAdapter mAdapter;
    private String mTag;

    // LOGGING VARIABLES:
    private static final String LOG_TAG = RestaurantListFragment.class.getSimpleName();

    // INSTANCE VARIABLES:
    private static final String INSTANCE_RESTAURANT_LIST = LOG_TAG + "_INSTANCE_RESTAURANT_LIST";
    private static final String INSTANCE_TAG = LOG_TAG + "_INSTANCE_TAG";
    private static final String INSTANCE_LATITUDE = LOG_TAG + "_INSTANCE_LATITUDE";
    private static final String INSTANCE_LONGITUDE = LOG_TAG + "_INSTANCE_LONGITUDE";
    private static final String INSTANCE_END_OF_LIST = LOG_TAG + "_INSTANCE_END_OF_LIST";

    // LOCATION VARIABLES:
    private double mLatitude;
    private double mLongitude;

    // QUERY VARIABLES:
    private static final String QUERY_LAT = "lat";
    private static final String QUERY_LNG = "lng";
    private static final String QUERY_OFFSET = "offset";
    private static final String QUERY_LIMIT = "limit";

    // RESTAURANT LIST VARIABLES:
    private static final int LIST_START_POSITION = 0;
    private static final int LIST_ITEM_LIMIT = 10;
    private boolean mIsEndOfList = false;
    private List<Restaurant> mRestaurantList;

    // RETROFIT VARIABLES:
    @Inject Retrofit mRetrofit;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public static RestaurantListFragment newInstance(double lat, double lng, String tag) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        fragment.mLatitude = lat;
        fragment.mLongitude = lng;
        fragment.mTag = tag;
        return fragment;
    }

    /** FRAGMENT LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((DashApplication) getActivity().getApplication()).getNetworkComponent().inject(this);
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
                initList();
            }
        } else {
            initList();
        }

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.fragmentRestaurantRecyclerView.setAdapter(null);
        mBinding.unbind();
    }

    /** FRAGMENT EXTENSION METHODS _____________________________________________________________ **/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mTag.equals(DashConstants.TAG_FAVORITES) &&
                resultCode == RestaurantDetailsActivity.RESULT_FAVORITE_REMOVED &&
                requestCode == RestaurantDetailsActivity.REQUEST_RESTAURANT_DETAILS) {

            int positionToRemove = data.getIntExtra(RestaurantDetailsActivity.BUNDLE_RESTAURANT_POSITION, -1);
            if (positionToRemove > -1) {
                mRestaurantList.remove(positionToRemove);
                mAdapter.updateRestaurantList(mRestaurantList);

                if (mRestaurantList.size() > 0) {
                    mAdapter.notifyDataSetChanged();
                } else {
                    mViewModel.setRestaurantListVisible(false);
                    mViewModel.setErrorVisibility(true);
                }
            }
        }
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

    /** INIT METHODS ___________________________________________________________________________ **/

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
        mLayoutManager.setInitialPrefetchItemCount(DashConstants.RECYCLER_VIEW_PREFETCH);

        mBinding.fragmentRestaurantRecyclerView.setLayoutManager(mLayoutManager);
        mBinding.fragmentRestaurantRecyclerView.setHasFixedSize(true);
        mBinding.fragmentRestaurantRecyclerView.setDrawingCacheEnabled(true);
        mBinding.fragmentRestaurantRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mBinding.fragmentRestaurantRecyclerView.addItemDecoration(new ListDividerItemDecoration(getContext()));

        RestaurantListScrollListener mScrollListener = new RestaurantListScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (!mTag.equals(DashConstants.TAG_FAVORITES) && !mIsEndOfList) {
                    queryRestaurantList(DashConstants.DOORDASH_LAT, DashConstants.DOORDASH_LNG,
                            currentPage * LIST_ITEM_LIMIT, LIST_ITEM_LIMIT);
                }
            }
        };
        mBinding.fragmentRestaurantRecyclerView.addOnScrollListener(mScrollListener);

        setRecyclerView();
    }

    private void setRecyclerView() {
        mViewModel.setRestaurantListVisible(true);
        mAdapter = new RestaurantListAdapter(mRestaurantList, this);
        mAdapter.setHasStableIds(true);
        mBinding.fragmentRestaurantRecyclerView.setAdapter(mAdapter);
    }

    /** RESTAURANT LIST METHODS ________________________________________________________________ **/

    private void initList() {
        mIsEndOfList = false;
        queryRestaurantList(DashConstants.DOORDASH_LAT, DashConstants.DOORDASH_LNG,
                LIST_START_POSITION, LIST_ITEM_LIMIT);
    }

    private void filterList() {
        List<FavoriteRestaurant> favoriteRestaurantList = FavoriteRestaurant.listAll(FavoriteRestaurant.class);
        if (favoriteRestaurantList != null && favoriteRestaurantList.size() > 0) {
            mRestaurantList = FilterUtils.filterRestaurantList(mRestaurantList, favoriteRestaurantList);
        } else {
            mRestaurantList.clear();
        }
    }

    private void updateList(List<Restaurant> updatedList) {
        mAdapter.updateRestaurantList(updatedList);
        mAdapter.notifyDataSetChanged();
    }

    /** INTENT METHODS _________________________________________________________________________ **/

    private void launchRestaurantDetailsIntent(int id, int position, String name, RestaurantDetail details) {
        Intent restaurantDetailsIntent = new Intent(getContext(), RestaurantDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(RestaurantDetailsActivity.BUNDLE_RESTAURANT_ID, id);
        bundle.putString(RestaurantDetailsActivity.BUNDLE_RESTAURANT_NAME, name);
        bundle.putParcelable(RestaurantDetailsActivity.BUNDLE_RESTAURANT_DETAILS, details);
        bundle.putInt(RestaurantDetailsActivity.BUNDLE_RESTAURANT_POSITION, position);
        restaurantDetailsIntent.putExtras(bundle);
        startActivityForResult(restaurantDetailsIntent, RestaurantDetailsActivity.REQUEST_RESTAURANT_DETAILS);
    }

    /** NETWORK METHODS ________________________________________________________________________ **/

    private void queryRestaurantList(Double lat, Double lng, final Integer offset, Integer limit) {
        RetrofitInterface restaurantListRequest = mRetrofit.create(RetrofitInterface.class);

        Map<String, String> requestParams = new HashMap<>();
        if (lat != null && lng != null) {
            requestParams.put(QUERY_LAT, String.valueOf(lat));
            requestParams.put(QUERY_LNG, String.valueOf(lng));
        }
        if (!mTag.equals(DashConstants.TAG_FAVORITES) && offset != null && limit != null) {
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
                        if (updatedRestaurantList.size() < LIST_ITEM_LIMIT - 2) {
                            mIsEndOfList = true;
                        }
                    }

                    // Filters the list if currently in "Favorites" view mode.
                    if (mTag.equals(DashConstants.TAG_FAVORITES)) {
                        filterList();
                    }

                    if (mRestaurantList != null && mRestaurantList.size() > 0) {
                        updateList(mRestaurantList);
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

    /** LISTENER METHODS _______________________________________________________________________ **/

    @Override
    public void onRestaurantClicked(int id, int position, String name, RestaurantDetail details) {
        launchRestaurantDetailsIntent(id, position, name, details);
    }
}
