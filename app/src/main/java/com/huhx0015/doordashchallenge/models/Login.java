package com.huhx0015.doordashchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class Login {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /** GET / SET METHODS ______________________________________________________________________ **/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
