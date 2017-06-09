package com.huhx0015.doordashchallenge.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.databinding.AdapterRestaurantListBinding;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.models.RestaurantDetail;
import com.huhx0015.doordashchallenge.utils.TagsUtils;
import com.huhx0015.doordashchallenge.view.listeners.RestaurantListAdapterListener;
import com.huhx0015.doordashchallenge.viewmodels.RestaurantListAdapterViewModel;
import java.util.List;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private Context mContext;
    private List<Restaurant> mRestaurantList;
    private RestaurantListAdapterListener mListener;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public RestaurantListAdapter(List<Restaurant> restaurant, RestaurantListAdapterListener listener,
                                 Context context) {
        this.mRestaurantList = restaurant;
        this.mListener = listener;
        this.mContext = context;
    }

    /** ADAPTER METHODS ________________________________________________________________________ **/

    @Override
    public RestaurantListAdapter.RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterRestaurantListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.adapter_restaurant_list, parent, false);
        return new RestaurantListAdapter.RestaurantListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.RestaurantListViewHolder holder, final int position) {
        final int id = mRestaurantList.get(position).getId();
        final String imageUrl = mRestaurantList.get(position).getCoverImgUrl();
        final String name = mRestaurantList.get(position).getName();
        String tags = "";
        final String status = mRestaurantList.get(position).getStatus();
        final int fee = mRestaurantList.get(position).getDeliveryFee();

        final List<String> tagList = mRestaurantList.get(position).getTags();
        if (tagList != null && tagList.size() > 0) {
            tags = TagsUtils.formatTags(tagList);
        }

        holder.bindView(imageUrl, name, tags, status, new RestaurantListAdapterViewModel.RestaurantListAdapterViewModelListener() {
            @Override
            public void onRowClicked() {
                RestaurantDetail details = new RestaurantDetail(imageUrl, name, tagList, status, id, fee);
                mListener.onRestaurantClicked(id, position, name, details);
            }
        });
    }

    @Override
    public void onViewRecycled(RestaurantListAdapter.RestaurantListViewHolder holder) {
        holder.mBinding.getViewModel().removeOnPropertyChangedCallback(null);
        holder.mBinding.setViewModel(null);
        holder.mBinding.executePendingBindings();
        super.onViewRecycled(holder);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mRestaurantList != null) {
            return mRestaurantList.size();
        } else {
            return 0;
        }
    }

    /** DATA METHODS ___________________________________________________________________________ **/

    public void updateRestaurantList(List<Restaurant> updatedList) {
        this.mRestaurantList = updatedList;
    }

    /** VIEWHOLDER CLASS _______________________________________________________________________ **/

    static class RestaurantListViewHolder extends RecyclerView.ViewHolder {

        /** CLASS VARIABLES ____________________________________________________________________ **/

        private AdapterRestaurantListBinding mBinding;

        /** CONSTRUCTOR METHODS ________________________________________________________________ **/

        RestaurantListViewHolder(AdapterRestaurantListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        /** BIND METHODS _______________________________________________________________________ **/

        private void bindView(String imageUrl, String name, String categories, String distance,
                              RestaurantListAdapterViewModel.RestaurantListAdapterViewModelListener listener) {
            RestaurantListAdapterViewModel viewModel = new RestaurantListAdapterViewModel(imageUrl,
                    name, categories, distance);
            viewModel.setListener(listener);
            mBinding.setViewModel(viewModel);
        }
    }
}