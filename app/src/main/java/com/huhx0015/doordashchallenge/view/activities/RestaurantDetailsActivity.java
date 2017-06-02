package com.huhx0015.doordashchallenge.view.activities;

import android.databinding.DataBindingUtil;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.databinding.ActivityRestaurantDetailsBinding;
import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.utils.TagsUtils;
import com.huhx0015.doordashchallenge.viewmodels.RestaurantDetailsViewModel;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestaurantDetailsActivity extends AppCompatActivity implements RestaurantDetailsViewModel.RestaurantDetailsViewModelListener {

    private static final String LOG_TAG = RestaurantDetailsActivity.class.getSimpleName();
    private static final int INVALID_RESTAURANT_ID = -1;

    public static final String BUNDLE_RESTAURANT_ID = LOG_TAG + "_BUNDLE_RESTAURANT_ID";
    public static final String BUNDLE_RESTAURANT_NAME = LOG_TAG + "_BUNDLE_RESTAURANT_NAME";
    public static final String BUNDLE_RESTAURANT_DETAILS = LOG_TAG + "_BUNDLE_RESTAURANT_DETAILS";

    private ActivityRestaurantDetailsBinding mBinding;
    private RestaurantDetailsViewModel mViewModel;
    private RestaurantDetail mRestaurantDetail;
    private int mRestaurantId;
    private String mRestaurantName;

    @Inject
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();

        if (savedInstanceState != null) {
            mRestaurantId = savedInstanceState.getInt(BUNDLE_RESTAURANT_ID, INVALID_RESTAURANT_ID);
            mRestaurantName = savedInstanceState.getString(BUNDLE_RESTAURANT_NAME);
            mRestaurantDetail = savedInstanceState.getParcelable(BUNDLE_RESTAURANT_DETAILS);
        } else {
            mRestaurantId = getIntent().getIntExtra(BUNDLE_RESTAURANT_ID, INVALID_RESTAURANT_ID);
            mRestaurantName = getIntent().getStringExtra(BUNDLE_RESTAURANT_NAME);
            mRestaurantDetail = getIntent().getParcelableExtra(BUNDLE_RESTAURANT_DETAILS);
        }

        initView();

        if (mRestaurantDetail == null) {
            queryRestaurantDetails();
        } else {
            setRestaurantDetails();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_RESTAURANT_ID, mRestaurantId);
        outState.putString(BUNDLE_RESTAURANT_NAME, mRestaurantName);
        outState.putParcelable(BUNDLE_RESTAURANT_DETAILS, mRestaurantDetail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_details);
        mViewModel = new RestaurantDetailsViewModel(this);
        mBinding.setViewModel(mViewModel);
    }

    private void initView() {
        initActionBar();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mRestaurantName != null ? mRestaurantName : getString(R.string.app_name));
        }
    }

    private void setRestaurantDetails() {
        String imageUrl = mRestaurantDetail.getCoverImgUrl();
        String tags = TagsUtils.formatTags(mRestaurantDetail.getTags());
        String status = mRestaurantDetail.getStatus();
        String rating = String.valueOf(mRestaurantDetail.getAverageRating());

        mViewModel.setRestaurantDetails(imageUrl, tags, status, rating);
        mViewModel.setRestaurantDetailsVisible(true);
    }

    private void queryRestaurantDetails() {
        RetrofitInterface restaurantDetailsRequest = mRetrofit.create(RetrofitInterface.class);
        Call<RestaurantDetail> call = restaurantDetailsRequest.getRestaurantDetail(String.valueOf(mRestaurantId));

        mViewModel.setProgressBarVisible(true);
        call.enqueue(new Callback<RestaurantDetail>() {
            @Override
            public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                mViewModel.setProgressBarVisible(false);

                mRestaurantDetail = response.body();
                if (mRestaurantDetail != null) {
                    setRestaurantDetails();
                } else {
                    mViewModel.setErrorVisibility(true);
                }

                Log.d(LOG_TAG, "onResponse(): Restaurant details query response received.");
            }

            @Override
            public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                mViewModel.setProgressBarVisible(false);
                mViewModel.setErrorVisibility(true);
                Log.e(LOG_TAG, "ERROR: onFailure(): Restaurant details query failed: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onAddFavoriteClicked() {

    }
}
