package com.huhx0015.doordashchallenge.view.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Adapted From:
 *
 * https://guides.codepath.com/android/Endless-Scrolling-with-AdapterViews-and-RecyclerView
 * https://gist.github.com/ssinss/e06f12ef66c51252563e
 */

public abstract class RestaurantListScrollListener extends RecyclerView.OnScrollListener {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // LOGGING VARIABLES:
    private static final String LOG_TAG = RestaurantListScrollListener.class.getSimpleName();

    // LAYOUT MANAGER VARIABLES:
    private LinearLayoutManager mLinearLayoutManager;

    // SCROLLING VARIABLES:
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int currentPage = 1; // The current offset index of data you have loaded.
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private int startingPageIndex = 0;// Sets the starting page index.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public RestaurantListScrollListener() {}

    public RestaurantListScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public RestaurantListScrollListener(int visibleThreshold, int startPage) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    protected RestaurantListScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    /** SCROLL METHODS _________________________________________________________________________ **/

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++;

            onLoadMore(currentPage);
            loading = true;
        }
    }

    // onLoadMore(): Defines the process for actually loading more data based on page.
    // Returns true if more data is being loaded; returns false if there is no more data to load.
    public abstract void onLoadMore(int currentPage);
}