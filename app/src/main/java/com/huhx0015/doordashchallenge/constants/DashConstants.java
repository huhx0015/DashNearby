package com.huhx0015.doordashchallenge.constants;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class DashConstants {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // HTTP VARIABLES:
    public static final String API_URL = "https://api.doordash.com/";
    public static final int HTTP_CLIENT_CACHE = 10 * 1024 * 1024;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;

    public static final String TOKEN_ID = "JWT ";

    // LOCATION VARIABLES:
    public static final double DOORDASH_LAT = 37.422740;
    public static final double DOORDASH_LNG = -122.139956;

    // TAG VARIABLES:
    public static final String TAG_DISCOVER = "TAG_DISCOVER";
    public static final String TAG_FAVORITES = "TAG_FAVORITES";

    // RECYCLERVIEW VARIABLES:
    public static final int RECYCLER_VIEW_PREFETCH = 6;
}
