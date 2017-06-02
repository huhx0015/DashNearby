
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

public class RestaurantDetail implements Parcelable {

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName("yelp_review_count")
    @Expose
    private int yelpReviewCount;

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

    @SerializedName("yelp_biz_id")
    @Expose
    private String yelpBizId;

    @SerializedName("delivery_radius")
    @Expose
    private int deliveryRadius;

    @SerializedName("inflation_rate")
    @Expose
    private double inflationRate;

    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    @SerializedName("show_store_menu_header_photo")
    @Expose
    private boolean showStoreMenuHeaderPhoto;

    @SerializedName("composite_score")
    @Expose
    private int compositeScore;

    @SerializedName("number_of_ratings")
    @Expose
    private int numberOfRatings;

    @SerializedName("status_type")
    @Expose
    private String statusType;

    @SerializedName("is_only_catering")
    @Expose
    private boolean isOnlyCatering;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("object_type")
    @Expose
    private String objectType;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("business")
    @Expose
    private Business business;

    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    @SerializedName("asap_time")
    @Expose
    private int asapTime;

    @SerializedName("yelp_rating")
    @Expose
    private double yelpRating;

    @SerializedName("extra_sos_delivery_fee")
    @Expose
    private int extraSosDeliveryFee;

    @SerializedName("business_id")
    @Expose
    private int businessId;

    @SerializedName("special_instructions_max_length")
    @Expose
    private Object specialInstructionsMaxLength;

    @SerializedName("cover_img_url")
    @Expose
    private String coverImgUrl;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("price_range")
    @Expose
    private int priceRange;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("show_suggested_items")
    @Expose
    private boolean showSuggestedItems;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("is_upsell_eligible")
    @Expose
    private boolean isUpsellEligible;

    @SerializedName("is_newly_added")
    @Expose
    private boolean isNewlyAdded;

    @SerializedName("is_good_for_group_orders")
    @Expose
    private boolean isGoodForGroupOrders;

    @SerializedName("service_rate")
    @Expose
    private double serviceRate;

    @SerializedName("merchant_promotions")
    @Expose
    private List<Object> merchantPromotions = null;

    @SerializedName("header_image_url")
    @Expose
    private Object headerImageUrl;

    public RestaurantDetail(String imageUrl, String name, List<String> tags, String status, int id, int fee) {
        this.coverImgUrl = imageUrl;
        this.name = name;
        this.tags = tags;
        this.status = status;
        this.id = id;
        this.deliveryFee = fee;
    }

    protected RestaurantDetail(Parcel in) {
        phoneNumber = in.readString();
        yelpReviewCount = in.readInt();
        deliveryFee = in.readInt();
        maxCompositeScore = in.readInt();
        id = in.readInt();
        averageRating = in.readDouble();
        yelpBizId = in.readString();
        deliveryRadius = in.readInt();
        inflationRate = in.readDouble();
        menus = in.createTypedArrayList(Menu.CREATOR);
        showStoreMenuHeaderPhoto = in.readByte() != 0;
        compositeScore = in.readInt();
        numberOfRatings = in.readInt();
        statusType = in.readString();
        isOnlyCatering = in.readByte() != 0;
        status = in.readString();
        objectType = in.readString();
        description = in.readString();
        business = in.readParcelable(Business.class.getClassLoader());
        tags = in.createStringArrayList();
        asapTime = in.readInt();
        yelpRating = in.readDouble();
        extraSosDeliveryFee = in.readInt();
        businessId = in.readInt();
        coverImgUrl = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
        priceRange = in.readInt();
        slug = in.readString();
        showSuggestedItems = in.readByte() != 0;
        name = in.readString();
        isUpsellEligible = in.readByte() != 0;
        isNewlyAdded = in.readByte() != 0;
        isGoodForGroupOrders = in.readByte() != 0;
        serviceRate = in.readDouble();
    }

    public static final Creator<RestaurantDetail> CREATOR = new Creator<RestaurantDetail>() {
        @Override
        public RestaurantDetail createFromParcel(Parcel in) {
            return new RestaurantDetail(in);
        }

        @Override
        public RestaurantDetail[] newArray(int size) {
            return new RestaurantDetail[size];
        }
    };

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getYelpReviewCount() {
        return yelpReviewCount;
    }

    public void setYelpReviewCount(int yelpReviewCount) {
        this.yelpReviewCount = yelpReviewCount;
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

    public String getYelpBizId() {
        return yelpBizId;
    }

    public void setYelpBizId(String yelpBizId) {
        this.yelpBizId = yelpBizId;
    }

    public int getDeliveryRadius() {
        return deliveryRadius;
    }

    public void setDeliveryRadius(int deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }

    public double getInflationRate() {
        return inflationRate;
    }

    public void setInflationRate(double inflationRate) {
        this.inflationRate = inflationRate;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public boolean isShowStoreMenuHeaderPhoto() {
        return showStoreMenuHeaderPhoto;
    }

    public void setShowStoreMenuHeaderPhoto(boolean showStoreMenuHeaderPhoto) {
        this.showStoreMenuHeaderPhoto = showStoreMenuHeaderPhoto;
    }

    public int getCompositeScore() {
        return compositeScore;
    }

    public void setCompositeScore(int compositeScore) {
        this.compositeScore = compositeScore;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
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

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
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

    public int getAsapTime() {
        return asapTime;
    }

    public void setAsapTime(int asapTime) {
        this.asapTime = asapTime;
    }

    public double getYelpRating() {
        return yelpRating;
    }

    public void setYelpRating(double yelpRating) {
        this.yelpRating = yelpRating;
    }

    public int getExtraSosDeliveryFee() {
        return extraSosDeliveryFee;
    }

    public void setExtraSosDeliveryFee(int extraSosDeliveryFee) {
        this.extraSosDeliveryFee = extraSosDeliveryFee;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public Object getSpecialInstructionsMaxLength() {
        return specialInstructionsMaxLength;
    }

    public void setSpecialInstructionsMaxLength(Object specialInstructionsMaxLength) {
        this.specialInstructionsMaxLength = specialInstructionsMaxLength;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
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

    public boolean isShowSuggestedItems() {
        return showSuggestedItems;
    }

    public void setShowSuggestedItems(boolean showSuggestedItems) {
        this.showSuggestedItems = showSuggestedItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsUpsellEligible() {
        return isUpsellEligible;
    }

    public void setIsUpsellEligible(boolean isUpsellEligible) {
        this.isUpsellEligible = isUpsellEligible;
    }

    public boolean isIsNewlyAdded() {
        return isNewlyAdded;
    }

    public void setIsNewlyAdded(boolean isNewlyAdded) {
        this.isNewlyAdded = isNewlyAdded;
    }

    public boolean isIsGoodForGroupOrders() {
        return isGoodForGroupOrders;
    }

    public void setIsGoodForGroupOrders(boolean isGoodForGroupOrders) {
        this.isGoodForGroupOrders = isGoodForGroupOrders;
    }

    public double getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(double serviceRate) {
        this.serviceRate = serviceRate;
    }

    public List<Object> getMerchantPromotions() {
        return merchantPromotions;
    }

    public void setMerchantPromotions(List<Object> merchantPromotions) {
        this.merchantPromotions = merchantPromotions;
    }

    public Object getHeaderImageUrl() {
        return headerImageUrl;
    }

    public void setHeaderImageUrl(Object headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeInt(yelpReviewCount);
        dest.writeInt(deliveryFee);
        dest.writeInt(maxCompositeScore);
        dest.writeInt(id);
        dest.writeDouble(averageRating);
        dest.writeString(yelpBizId);
        dest.writeInt(deliveryRadius);
        dest.writeDouble(inflationRate);
        dest.writeTypedList(menus);
        dest.writeByte((byte) (showStoreMenuHeaderPhoto ? 1 : 0));
        dest.writeInt(compositeScore);
        dest.writeInt(numberOfRatings);
        dest.writeString(statusType);
        dest.writeByte((byte) (isOnlyCatering ? 1 : 0));
        dest.writeString(status);
        dest.writeString(objectType);
        dest.writeString(description);
        dest.writeParcelable(business, flags);
        dest.writeStringList(tags);
        dest.writeInt(asapTime);
        dest.writeDouble(yelpRating);
        dest.writeInt(extraSosDeliveryFee);
        dest.writeInt(businessId);
        dest.writeString(coverImgUrl);
        dest.writeParcelable(address, flags);
        dest.writeInt(priceRange);
        dest.writeString(slug);
        dest.writeByte((byte) (showSuggestedItems ? 1 : 0));
        dest.writeString(name);
        dest.writeByte((byte) (isUpsellEligible ? 1 : 0));
        dest.writeByte((byte) (isNewlyAdded ? 1 : 0));
        dest.writeByte((byte) (isGoodForGroupOrders ? 1 : 0));
        dest.writeDouble(serviceRate);
    }
}
