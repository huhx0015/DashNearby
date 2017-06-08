
package com.huhx0015.doordashchallenge.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("social_accounts")
    @Expose
    private List<Object> socialAccounts = null;
    @SerializedName("object_type")
    @Expose
    private String objectType;
    @SerializedName("is_repeat_consumer")
    @Expose
    private boolean isRepeatConsumer;
    @SerializedName("default_card")
    @Expose
    private DefaultCard defaultCard;
    @SerializedName("referral_code")
    @Expose
    private String referralCode;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("order_count")
    @Expose
    private int orderCount;
    @SerializedName("receive_marketing_push_notifications")
    @Expose
    private boolean receiveMarketingPushNotifications;
    @SerializedName("default_address")
    @Expose
    private DefaultAddress defaultAddress;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("has_usable_password")
    @Expose
    private boolean hasUsablePassword;
    @SerializedName("referrer_amount")
    @Expose
    private int referrerAmount;
    @SerializedName("account_credits")
    @Expose
    private int accountCredits;
    @SerializedName("receive_text_notifications")
    @Expose
    private boolean receiveTextNotifications;
    @SerializedName("default_country")
    @Expose
    private String defaultCountry;
    @SerializedName("receive_push_notifications")
    @Expose
    private boolean receivePushNotifications;
    @SerializedName("has_accepted_latest_terms_of_service")
    @Expose
    private boolean hasAcceptedLatestTermsOfService;
    @SerializedName("latest_version_of_terms_of_service")
    @Expose
    private String latestVersionOfTermsOfService;
    @SerializedName("social_data")
    @Expose
    private List<Object> socialData = null;
    @SerializedName("only_ordered_once")
    @Expose
    private boolean onlyOrderedOnce;
    @SerializedName("is_guest")
    @Expose
    private boolean isGuest;
    @SerializedName("default_substitution_preference")
    @Expose
    private String defaultSubstitutionPreference;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Object> getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(List<Object> socialAccounts) {
        this.socialAccounts = socialAccounts;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public boolean isIsRepeatConsumer() {
        return isRepeatConsumer;
    }

    public void setIsRepeatConsumer(boolean isRepeatConsumer) {
        this.isRepeatConsumer = isRepeatConsumer;
    }

    public DefaultCard getDefaultCard() {
        return defaultCard;
    }

    public void setDefaultCard(DefaultCard defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public boolean isReceiveMarketingPushNotifications() {
        return receiveMarketingPushNotifications;
    }

    public void setReceiveMarketingPushNotifications(boolean receiveMarketingPushNotifications) {
        this.receiveMarketingPushNotifications = receiveMarketingPushNotifications;
    }

    public DefaultAddress getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(DefaultAddress defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isHasUsablePassword() {
        return hasUsablePassword;
    }

    public void setHasUsablePassword(boolean hasUsablePassword) {
        this.hasUsablePassword = hasUsablePassword;
    }

    public int getReferrerAmount() {
        return referrerAmount;
    }

    public void setReferrerAmount(int referrerAmount) {
        this.referrerAmount = referrerAmount;
    }

    public int getAccountCredits() {
        return accountCredits;
    }

    public void setAccountCredits(int accountCredits) {
        this.accountCredits = accountCredits;
    }

    public boolean isReceiveTextNotifications() {
        return receiveTextNotifications;
    }

    public void setReceiveTextNotifications(boolean receiveTextNotifications) {
        this.receiveTextNotifications = receiveTextNotifications;
    }

    public String getDefaultCountry() {
        return defaultCountry;
    }

    public void setDefaultCountry(String defaultCountry) {
        this.defaultCountry = defaultCountry;
    }

    public boolean isReceivePushNotifications() {
        return receivePushNotifications;
    }

    public void setReceivePushNotifications(boolean receivePushNotifications) {
        this.receivePushNotifications = receivePushNotifications;
    }

    public boolean isHasAcceptedLatestTermsOfService() {
        return hasAcceptedLatestTermsOfService;
    }

    public void setHasAcceptedLatestTermsOfService(boolean hasAcceptedLatestTermsOfService) {
        this.hasAcceptedLatestTermsOfService = hasAcceptedLatestTermsOfService;
    }

    public String getLatestVersionOfTermsOfService() {
        return latestVersionOfTermsOfService;
    }

    public void setLatestVersionOfTermsOfService(String latestVersionOfTermsOfService) {
        this.latestVersionOfTermsOfService = latestVersionOfTermsOfService;
    }

    public List<Object> getSocialData() {
        return socialData;
    }

    public void setSocialData(List<Object> socialData) {
        this.socialData = socialData;
    }

    public boolean isOnlyOrderedOnce() {
        return onlyOrderedOnce;
    }

    public void setOnlyOrderedOnce(boolean onlyOrderedOnce) {
        this.onlyOrderedOnce = onlyOrderedOnce;
    }

    public boolean isIsGuest() {
        return isGuest;
    }

    public void setIsGuest(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public String getDefaultSubstitutionPreference() {
        return defaultSubstitutionPreference;
    }

    public void setDefaultSubstitutionPreference(String defaultSubstitutionPreference) {
        this.defaultSubstitutionPreference = defaultSubstitutionPreference;
    }

}
