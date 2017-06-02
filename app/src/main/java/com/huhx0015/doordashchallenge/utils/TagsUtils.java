package com.huhx0015.doordashchallenge.utils;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class TagsUtils {

    public static String formatTags(@NonNull List<String> tagList) {
        StringBuilder tagBuilder = new StringBuilder();

        int count = 0;
        for (String tag : tagList) {
            tagBuilder.append(tag);
            if (count++ < tagList.size() - 1) {
                tagBuilder.append(", ");
            }
        }

        return tagBuilder.toString();
    }
}
