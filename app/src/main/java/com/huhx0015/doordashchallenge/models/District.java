
package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("shortname")
    @Expose
    private String shortname;
    @SerializedName("first_delivery_price")
    @Expose
    private int firstDeliveryPrice;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public int getFirstDeliveryPrice() {
        return firstDeliveryPrice;
    }

    public void setFirstDeliveryPrice(int firstDeliveryPrice) {
        this.firstDeliveryPrice = firstDeliveryPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
