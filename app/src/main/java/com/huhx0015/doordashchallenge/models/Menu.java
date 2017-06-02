
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

public class Menu implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("is_catering")
    @Expose
    private boolean isCatering;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("status_type")
    @Expose
    private String statusType;

    protected Menu(Parcel in) {
        status = in.readString();
        subtitle = in.readString();
        name = in.readString();
        isCatering = in.readByte() != 0;
        id = in.readInt();
        statusType = in.readString();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCatering() {
        return isCatering;
    }

    public void setCatering(boolean catering) {
        isCatering = catering;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(subtitle);
        dest.writeString(name);
        dest.writeByte((byte) (isCatering ? 1 : 0));
        dest.writeInt(id);
        dest.writeString(statusType);
    }
}
