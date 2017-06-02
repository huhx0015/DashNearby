package com.huhx0015.doordashchallenge.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.databinding.AdapterRestaurantListBinding;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.viewmodels.RestaurantListAdapterViewModel;
import java.util.List;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {

    private List<Restaurant> mRestaurant;

    public RestaurantListAdapter(List<Restaurant> restaurant) {
        this.mRestaurant = restaurant;
    }

    @Override
    public RestaurantListAdapter.RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterRestaurantListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.adapter_restaurant_list, parent, false);
        return new RestaurantListAdapter.RestaurantListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.RestaurantListViewHolder holder, int position) {
        String imageUrl = mRestaurant.get(position).getUrl();
        String name = mRestaurant.get(position).getName();
        String tags = "";
        String status = mRestaurant.get(position).getStatus();

        List<String> tagList = mRestaurant.get(position).getTags();
        if (tagList != null && tagList.size() > 0) {
            StringBuilder tagBuilder = new StringBuilder();

            int count = 0;
            for (String tag : tagList) {
                tagBuilder.append(tag);
                if (count < tagList.size()) {
                    tagBuilder.append(", ");
                }
            }

            tags = tagBuilder.toString();
        }

        holder.bindView(imageUrl, name, tags, status);
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
        if (mRestaurant != null) {
            return mRestaurant.size();
        } else {
            return 0;
        }
    }

    static class RestaurantListViewHolder extends RecyclerView.ViewHolder {

        private AdapterRestaurantListBinding mBinding;

        RestaurantListViewHolder(AdapterRestaurantListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        private void bindView(String imageUrl, String name, String categories, String distance) {
            RestaurantListAdapterViewModel viewModel =
                    new RestaurantListAdapterViewModel(imageUrl, name, categories, distance);
            mBinding.setViewModel(viewModel);
        }
    }
}
