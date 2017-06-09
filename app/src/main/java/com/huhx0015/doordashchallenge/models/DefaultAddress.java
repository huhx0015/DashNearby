package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultAddress {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("manual_lng")
    @Expose
    private double manualLng;
    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("subpremise")
    @Expose
    private String subpremise;
    @SerializedName("object_type")
    @Expose
    private String objectType;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("printable_address")
    @Expose
    private String printableAddress;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("shortname")
    @Expose
    private String shortname;
    @SerializedName("submarket")
    @Expose
    private String submarket;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("driver_instructions")
    @Expose
    private String driverInstructions;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("submarket_id")
    @Expose
    private int submarketId;
    @SerializedName("manual_lat")
    @Expose
    private double manualLat;
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;

    /** GET / SET METHODS ______________________________________________________________________ **/

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getManualLng() {
        return manualLng;
    }

    public void setManualLng(double manualLng) {
        this.manualLng = manualLng;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getSubpremise() {
        return subpremise;
    }

    public void setSubpremise(String subpremise) {
        this.subpremise = subpremise;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrintableAddress() {
        return printableAddress;
    }

    public void setPrintableAddress(String printableAddress) {
        this.printableAddress = printableAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getSubmarket() {
        return submarket;
    }

    public void setSubmarket(String submarket) {
        this.submarket = submarket;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDriverInstructions() {
        return driverInstructions;
    }

    public void setDriverInstructions(String driverInstructions) {
        this.driverInstructions = driverInstructions;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getSubmarketId() {
        return submarketId;
    }

    public void setSubmarketId(int submarketId) {
        this.submarketId = submarketId;
    }

    public double getManualLat() {
        return manualLat;
    }

    public void setManualLat(double manualLat) {
        this.manualLat = manualLat;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
