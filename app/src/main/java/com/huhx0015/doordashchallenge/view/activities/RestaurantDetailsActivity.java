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
import com.huhx0015.doordashchallenge.entities.FavoriteRestaurant;
import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.utils.TagsUtils;
import com.huhx0015.doordashchallenge.viewmodels.RestaurantDetailsViewModel;
import java.util.List;
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

public class RestaurantDetailsActivity extends AppCompatActivity implements RestaurantDetailsViewModel.RestaurantDetailsViewModelListener {

    private static final String LOG_TAG = RestaurantDetailsActivity.class.getSimpleName();
    private static final int INVALID_ID = -1;

    public static final String BUNDLE_RESTAURANT_ID = LOG_TAG + "_BUNDLE_RESTAURANT_ID";
    public static final String BUNDLE_RESTAURANT_NAME = LOG_TAG + "_BUNDLE_RESTAURANT_NAME";
    public static final String BUNDLE_RESTAURANT_DETAILS = LOG_TAG + "_BUNDLE_RESTAURANT_DETAILS";
    public static final String BUNDLE_IS_FAVORITE = LOG_TAG + "_BUNDLE_IS_FAVORITE";
    public static final String BUNDLE_DATABASE_ID = LOG_TAG + "_DATABASE_ID";

    private ActivityRestaurantDetailsBinding mBinding;
    private RestaurantDetailsViewModel mViewModel;
    private RestaurantDetail mRestaurantDetail;
    private boolean mIsFavorite;
    private int mRestaurantId;
    private long mDatabaseId;
    private String mRestaurantName;

    @Inject
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();

        if (savedInstanceState != null) {
            mRestaurantId = savedInstanceState.getInt(BUNDLE_RESTAURANT_ID, INVALID_ID);
            mRestaurantName = savedInstanceState.getString(BUNDLE_RESTAURANT_NAME);
            mRestaurantDetail = savedInstanceState.getParcelable(BUNDLE_RESTAURANT_DETAILS);
            mIsFavorite = savedInstanceState.getBoolean(BUNDLE_IS_FAVORITE);
            mDatabaseId = savedInstanceState.getLong(BUNDLE_DATABASE_ID);
        } else {
            mRestaurantId = getIntent().getIntExtra(BUNDLE_RESTAURANT_ID, INVALID_ID);
            mRestaurantName = getIntent().getStringExtra(BUNDLE_RESTAURANT_NAME);
            mRestaurantDetail = getIntent().getParcelableExtra(BUNDLE_RESTAURANT_DETAILS);
            mIsFavorite = checkFavorite();
            mDatabaseId = INVALID_ID;
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
        outState.putBoolean(BUNDLE_IS_FAVORITE, mIsFavorite);
        outState.putLong(BUNDLE_DATABASE_ID, mDatabaseId);
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

    private boolean checkFavorite() {

        // TODO: Sugar ORM is not compatible with Instant Run! Instant Run must be disabled first.
        List<FavoriteRestaurant> favoriteRestaurantList = FavoriteRestaurant.listAll(FavoriteRestaurant.class);
        if (favoriteRestaurantList != null && favoriteRestaurantList.size() > 0) {

            for (FavoriteRestaurant favoriteRestaurant : favoriteRestaurantList) {
                if (favoriteRestaurant.getRestaurantId() == mRestaurantId) {
                    Log.d(LOG_TAG, "checkFavorite(): Favorite ID match found.");

                    mIsFavorite = true;
                    mDatabaseId = favoriteRestaurant.getId();
                    mViewModel.setIsFavorite(true, this);
                    return true;
                }
            }
        }
        mViewModel.setIsFavorite(false, this);
        return false;
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

        // TODO: Sugar ORM is not compatible with Instant Run! Instant Run must be disabled first.
        if (mIsFavorite) {
            FavoriteRestaurant favoriteRestaurant = FavoriteRestaurant.findById(FavoriteRestaurant.class, mDatabaseId);
            if (favoriteRestaurant != null) {
                favoriteRestaurant.delete();
            }
            mIsFavorite = false;
            mDatabaseId = INVALID_ID;
            mViewModel.setIsFavorite(false, this);
            Log.d(LOG_TAG, "onAddFavoriteClicked(): Removed from favorites.");
        } else {
            FavoriteRestaurant favoriteRestaurant = new FavoriteRestaurant(mRestaurantName, mRestaurantId);
            favoriteRestaurant.save();
            mIsFavorite = true;
            mDatabaseId = favoriteRestaurant.getId();
            mViewModel.setIsFavorite(true, this);
            Log.d(LOG_TAG, "onAddFavoriteClicked(): Added to favorites.");
        }
    }
}
