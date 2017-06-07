package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class Token {

    @SerializedName("token")
    @Expose
    public String token;
}
