package com.huhx0015.doordashchallenge.utils;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class TagsUtilsTest {

    @Test
    public void tags_isCorrect() throws Exception {
        List<String> testTags = new ArrayList<>();
        testTags.add("Italian");
        testTags.add("Chinese");
        testTags.add("Pizza");
        testTags.add("Korean");

        assertEquals(TagsUtils.formatTags(testTags), "Italian, Chinese, Pizza, Korean");
    }
}