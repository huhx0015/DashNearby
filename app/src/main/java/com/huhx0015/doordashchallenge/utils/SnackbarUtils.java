package com.huhx0015.doordashchallenge.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Michael Yoon Huh on 6/8/2017.
 */

public class SnackbarUtils {

    /** SNACKBAR METHODS _______________________________________________________________________ **/

    public static void displaySnackbar(View view, String message, int length, int color) {
        Snackbar snackbar = Snackbar.make(view, message, length);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(color);
        snackbar.show();
    }
}