package com.huhx0015.doordashchallenge.utils;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class TagsUtilsTest {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private static final String TEST_TAG_1 = "Italian";
    private static final String TEST_TAG_2 = "Chinese";
    private static final String TEST_TAG_3 = "Pizza";
    private static final String TEST_TAG_4 = "Korean";
    private static final String TEST_FORMATTED_TAG = "Italian, Chinese, Pizza, Korean";

    /** TEST METHODS ___________________________________________________________________________ **/

    @Test
    public void testFormatTags() throws Exception {
        List<String> testTags = new ArrayList<>();
        testTags.add(TEST_TAG_1);
        testTags.add(TEST_TAG_2);
        testTags.add(TEST_TAG_3);
        testTags.add(TEST_TAG_4);

        assertEquals(TagsUtils.formatTags(testTags), TEST_FORMATTED_TAG);
    }
}