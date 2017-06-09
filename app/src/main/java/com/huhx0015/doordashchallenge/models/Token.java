package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class Token {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("non_field_errors")
    @Expose
    public String nonFieldErrors;
}
