package com.huhx0015.doordashchallenge.viewmodels;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantDetailsViewModelTest {

    private static final String TEST_IMAGE_URL = "http://www.test.com/test.jpg";
    private static final String TEST_TAGS = "Chinese, Korean";
    private static final String TEST_STATUS = "30 minutes";
    private static final String TEST_FEE = "5.99";

    @Mock
    private RestaurantDetailsViewModel mViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mViewModel = new RestaurantDetailsViewModel();
    }

    @Test
    public void testSetRestaurantDetailsVisibility() throws Exception {
        Assert.assertEquals(false, mViewModel.getRestaurantDetailsVisible());
        mViewModel.setRestaurantDetailsVisible(true);
        Assert.assertEquals(true, mViewModel.getRestaurantDetailsVisible());
    }

    @Test
    public void testSetProgressVisibility() throws Exception {
        Assert.assertEquals(false, mViewModel.getProgressBarVisible());
        mViewModel.setProgressBarVisible(true);
        Assert.assertEquals(true, mViewModel.getProgressBarVisible());
    }

    @Test
    public void testSetErrorVisibility() throws Exception {
        Assert.assertEquals(false, mViewModel.getErrorVisible());
        mViewModel.setErrorVisibility(true);
        Assert.assertEquals(true, mViewModel.getErrorVisible());
    }

    @Test
    public void testRestaurantDetails() throws Exception {
        mViewModel.setRestaurantDetails(TEST_IMAGE_URL, TEST_TAGS, TEST_STATUS, TEST_FEE);
        Assert.assertEquals(TEST_IMAGE_URL, mViewModel.getImageUrl());
        Assert.assertEquals(TEST_TAGS, mViewModel.getTags());
        Assert.assertEquals(TEST_STATUS, mViewModel.getStatus());
        Assert.assertEquals(TEST_FEE, mViewModel.getFee());
    }
}
