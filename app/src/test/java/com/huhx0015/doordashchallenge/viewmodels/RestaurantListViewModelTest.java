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
public class RestaurantListViewModelTest {

    @Mock
    private RestaurantListViewModel mViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mViewModel = new RestaurantListViewModel();
    }

    @Test
    public void testSetRestaurantListVisibility() throws Exception {
        Assert.assertEquals(false, mViewModel.getRestaurantListVisible());
        mViewModel.setRestaurantListVisible(true);
        Assert.assertEquals(true, mViewModel.getRestaurantListVisible());
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
}
