
package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 *
 * Auto-generated model with jsonschema2pojo: http://www.jsonschema2pojo.org/
 */

public class Business {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

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
