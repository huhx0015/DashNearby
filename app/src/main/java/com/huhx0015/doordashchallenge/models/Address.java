
package com.huhx0015.doordashchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 *
 * Auto-generated model with jsonschema2pojo: http://www.jsonschema2pojo.org/
 */

public class Address implements Parcelable {

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("subpremise")
    @Expose
    private String subpremise;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("printable_address")
    @Expose
    private String printableAddress;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("street")
    @Expose
    private String street;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("lat")
    @Expose
    private double lat;

    @SerializedName("lng")
    @Expose
    private double lng;

    @SerializedName("shortname")
    @Expose
    private String shortname;

    @SerializedName("zip_code")
    @Expose
    private String zipCode;

    protected Address(Parcel in) {
        city = in.readString();
        subpremise = in.readString();
        id = in.readInt();
        printableAddress = in.readString();
        state = in.readString();
        street = in.readString();
        country = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        shortname = in.readString();
        zipCode = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSubpremise() {
        return subpremise;
    }

    public void setSubpremise(String subpremise) {
        this.subpremise = subpremise;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(subpremise);
        dest.writeInt(id);
        dest.writeString(printableAddress);
        dest.writeString(state);
        dest.writeString(street);
        dest.writeString(country);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(shortname);
        dest.writeString(zipCode);
    }
}
