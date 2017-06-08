
package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultCard {

    @SerializedName("exp_month")
    @Expose
    private String expMonth;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("has_existing_card")
    @Expose
    private boolean hasExistingCard;
    @SerializedName("stripe_id")
    @Expose
    private String stripeId;
    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;
    @SerializedName("last4")
    @Expose
    private String last4;
    @SerializedName("dynamic_last4")
    @Expose
    private String dynamicLast4;
    @SerializedName("exp_year")
    @Expose
    private String expYear;
    @SerializedName("consumer")
    @Expose
    private int consumer;
    @SerializedName("id")
    @Expose
    private int id;

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasExistingCard() {
        return hasExistingCard;
    }

    public void setHasExistingCard(boolean hasExistingCard) {
        this.hasExistingCard = hasExistingCard;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getDynamicLast4() {
        return dynamicLast4;
    }

    public void setDynamicLast4(String dynamicLast4) {
        this.dynamicLast4 = dynamicLast4;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public int getConsumer() {
        return consumer;
    }

    public void setConsumer(int consumer) {
        this.consumer = consumer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
