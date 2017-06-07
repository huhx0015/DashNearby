package com.huhx0015.doordashchallenge;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityViewModelTest {

    @Mock
    



 +    private RestaurantDetailsViewModel mViewModel;
 +
         +    @Before
 +    public void setUp() {
        +        MockitoAnnotations.initMocks(this);
        +        mViewModel = new RestaurantDetailsViewModel();
        +    }

}
