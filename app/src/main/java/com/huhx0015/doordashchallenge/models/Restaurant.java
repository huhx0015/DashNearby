
package com.huhx0015.doordashchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 *
 * Auto-generated model with jsonschema2pojo: http://www.jsonschema2pojo.org/
 */

public class Restaurant implements Parcelable {

    @SerializedName("is_time_surging")
    @Expose
    private boolean isTimeSurging;

    @SerializedName("max_order_size")
    @Expose
    private Object maxOrderSize;

    @SerializedName("delivery_fee")
    @Expose
    private int deliveryFee;

    @SerializedName("max_composite_score")
    @Expose
    private int maxCompositeScore;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("average_rating")
    @Expose
    private double averageRating;

    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    @SerializedName("composite_score")
    @Expose
    private int compositeScore;

    @SerializedName("status_type")
    @Expose
    private String statusType;

    @SerializedName("is_only_catering")
    @Expose
    private boolean isOnlyCatering;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("number_of_ratings")
    @Expose
    private int numberOfRatings;

    @SerializedName("asap_time")
    @Expose
    private int asapTime;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("business")
    @Expose
    private Business business;

    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    @SerializedName("yelp_review_count")
    @Expose
    private int yelpReviewCount;

    @SerializedName("business_id")
    @Expose
    private int businessId;

    @SerializedName("extra_sos_delivery_fee")
    @Expose
    private int extraSosDeliveryFee;

    @SerializedName("yelp_rating")
    @Expose
    private double yelpRating;

    @SerializedName("cover_img_url")
    @Expose
    private String coverImgUrl;

    @SerializedName("header_img_url")
    @Expose
    private String headerImgUrl;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("price_range")
    @Expose
    private int priceRange;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("is_newly_added")
    @Expose
    private boolean isNewlyAdded;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("service_rate")
    @Expose
    private double serviceRate;

    @SerializedName("promotion")
    @Expose
    private Object promotion;

    @SerializedName("featured_category_description")
    @Expose
    private Object featuredCategoryDescription;

    public Restaurant() {}

    public Restaurant(int id) {
        this.id = id;
    }

    protected Restaurant(Parcel in) {
        isTimeSurging = in.readByte() != 0;
        deliveryFee = in.readInt();
        maxCompositeScore = in.readInt();
        id = in.readInt();
        averageRating = in.readDouble();
        menus = in.createTypedArrayList(Menu.CREATOR);
        compositeScore = in.readInt();
        statusType = in.readString();
        isOnlyCatering = in.readByte() != 0;
        status = in.readString();
        numberOfRatings = in.readInt();
        asapTime = in.readInt();
        description = in.readString();
        business = in.readParcelable(Business.class.getClassLoader());
        tags = in.createStringArrayList();
        yelpReviewCount = in.readInt();
        businessId = in.readInt();
        extraSosDeliveryFee = in.readInt();
        yelpRating = in.readDouble();
        coverImgUrl = in.readString();
        headerImgUrl = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
        priceRange = in.readInt();
        slug = in.readString();
        name = in.readString();
        isNewlyAdded = in.readByte() != 0;
        url = in.readString();
        serviceRate = in.readDouble();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public boolean isIsTimeSurging() {
        return isTimeSurging;
    }

    public void setIsTimeSurging(boolean isTimeSurging) {
        this.isTimeSurging = isTimeSurging;
    }

    public Object getMaxOrderSize() {
        return maxOrderSize;
    }

    public void setMaxOrderSize(Object maxOrderSize) {
        this.maxOrderSize = maxOrderSize;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getMaxCompositeScore() {
        return maxCompositeScore;
    }

    public void setMaxCompositeScore(int maxCompositeScore) {
        this.maxCompositeScore = maxCompositeScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getCompositeScore() {
        return compositeScore;
    }

    public void setCompositeScore(int compositeScore) {
        this.compositeScore = compositeScore;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public boolean isIsOnlyCatering() {
        return isOnlyCatering;
    }

    public void setIsOnlyCatering(boolean isOnlyCatering) {
        this.isOnlyCatering = isOnlyCatering;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public int getAsapTime() {
        return asapTime;
    }

    public void setAsapTime(int asapTime) {
        this.asapTime = asapTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getYelpReviewCount() {
        return yelpReviewCount;
    }

    public void setYelpReviewCount(int yelpReviewCount) {
        this.yelpReviewCount = yelpReviewCount;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getExtraSosDeliveryFee() {
        return extraSosDeliveryFee;
    }

    public void setExtraSosDeliveryFee(int extraSosDeliveryFee) {
        this.extraSosDeliveryFee = extraSosDeliveryFee;
    }

    public double getYelpRating() {
        return yelpRating;
    }

    public void setYelpRating(double yelpRating) {
        this.yelpRating = yelpRating;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsNewlyAdded() {
        return isNewlyAdded;
    }

    public void setIsNewlyAdded(boolean isNewlyAdded) {
        this.isNewlyAdded = isNewlyAdded;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(double serviceRate) {
        this.serviceRate = serviceRate;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public Object getFeaturedCategoryDescription() {
        return featuredCategoryDescription;
    }

    public void setFeaturedCategoryDescription(Object featuredCategoryDescription) {
        this.featuredCategoryDescription = featuredCategoryDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isTimeSurging ? 1 : 0));
        dest.writeInt(deliveryFee);
        dest.writeInt(maxCompositeScore);
        dest.writeInt(id);
        dest.writeDouble(averageRating);
        dest.writeTypedList(menus);
        dest.writeInt(compositeScore);
        dest.writeString(statusType);
        dest.writeByte((byte) (isOnlyCatering ? 1 : 0));
        dest.writeString(status);
        dest.writeInt(numberOfRatings);
        dest.writeInt(asapTime);
        dest.writeString(description);
        dest.writeParcelable(business, flags);
        dest.writeStringList(tags);
        dest.writeInt(yelpReviewCount);
        dest.writeInt(businessId);
        dest.writeInt(extraSosDeliveryFee);
        dest.writeDouble(yelpRating);
        dest.writeString(coverImgUrl);
        dest.writeString(headerImgUrl);
        dest.writeParcelable(address, flags);
        dest.writeInt(priceRange);
        dest.writeString(slug);
        dest.writeString(name);
        dest.writeByte((byte) (isNewlyAdded ? 1 : 0));
        dest.writeString(url);
        dest.writeDouble(serviceRate);
    }
}
