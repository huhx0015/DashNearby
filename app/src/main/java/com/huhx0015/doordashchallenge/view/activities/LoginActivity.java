package com.huhx0015.doordashchallenge.view.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.data.DashPreferences;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.application.RestaurantApplication;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.databinding.ActivityLoginBinding;
import com.huhx0015.doordashchallenge.models.Login;
import com.huhx0015.doordashchallenge.models.Token;
import com.huhx0015.doordashchallenge.models.User;
import com.huhx0015.doordashchallenge.utils.SnackbarUtils;
import com.huhx0015.doordashchallenge.viewmodels.LoginActivityViewModel;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityViewModel.LoginActivityViewModelListener {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // DATABINDING VARIABLES:
    private ActivityLoginBinding mBinding;
    private LoginActivityViewModel mViewModel;

    // LOGGING VARIABLES:
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    // INSTANCE VARIABLES:
    private static final String INSTANCE_EMAIL = LOG_TAG + "_INSTANCE_EMAIL";
    private static final String INSTANCE_PASSWORD = LOG_TAG + "_INSTANCE_PASSWORD";

    // LOGIN VARIABLES:
    private String mEmail;
    private String mPassword;

    // RETROFIT VARIABLES:
    @Inject Retrofit mRetrofit;

    /** ACTIVITY LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RestaurantApplication) getApplication()).getNetworkComponent().inject(this);

        if (savedInstanceState != null) {
            this.mEmail = savedInstanceState.getString(INSTANCE_EMAIL);
            this.mPassword = savedInstanceState.getString(INSTANCE_PASSWORD);
        }

        initBinding();
        initTextWatchers();
        initLoginState();
    }

    /** ACTIVITY EXTENSION METHODS _____________________________________________________________ **/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_EMAIL, mEmail);
        outState.putString(INSTANCE_PASSWORD, mPassword);
    }

    /** INIT METHODS ___________________________________________________________________________ **/

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mViewModel = new LoginActivityViewModel();
        mViewModel.setListener(this);
        mBinding.setViewModel(mViewModel);
    }

    private void initTextWatchers() {
        mBinding.loginEmailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEmail = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mBinding.loginPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPassword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initLoginState() {
        String token = DashPreferences.getAuthToken(this);
        if (token != null) {
            getUserData(token);
        } else {
            mViewModel.setLoginFieldVisibility(true);
        }
    }

    private void handleError(String message) {
        mViewModel.setLoginFieldVisibility(true);
        mViewModel.setProgressBarVisibility(false);

        SnackbarUtils.displaySnackbar(mBinding.getRoot(), message, Snackbar.LENGTH_SHORT,
                ContextCompat.getColor(this, R.color.colorAccent));
    }

    /** INTENT METHODS _________________________________________________________________________ **/

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /** NETWORK METHODS ________________________________________________________________________ **/

    private void getAuthToken() {
        Log.d(LOG_TAG, "getAuthToken(): Email: " + mEmail + " | mPassword: " + mPassword);

        RetrofitInterface request = mRetrofit.create(RetrofitInterface.class);
        Call<Token> call = request.getAuthToken(new Login(mEmail, mPassword));

        mViewModel.setLoginFieldVisibility(false);
        mViewModel.setProgressBarVisibility(true);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();

                    if (token != null && token.token != null) {
                        DashPreferences.setAuthToken(token.token, LoginActivity.this);
                        getUserData(token.token);
                    } else{
                        handleError(getString(R.string.login_error));
                    }
                } else {
                    if (response.code() == RestaurantConstants.HTTP_BAD_REQUEST) {
                        handleError(getString(R.string.login_wrong_credentials));
                    } else if (response.code() == RestaurantConstants.HTTP_FORBIDDEN) {
                        String previousToken = DashPreferences.getAuthToken(LoginActivity.this);
                        if (previousToken != null) {
                            refreshToken(previousToken);
                        } else {
                            handleError(getString(R.string.login_error));
                        }
                    } else {
                        handleError(getString(R.string.login_error));
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                handleError(getString(R.string.login_error));
            }
        });
    }

    private void getUserData(String token) {
        RetrofitInterface request = mRetrofit.create(RetrofitInterface.class);
        Call<User> call = request.getUser(RestaurantConstants.TOKEN_ID + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();

                    if (user != null) {
                        launchMainActivity();
                    } else {
                        handleError(getString(R.string.login_error));
                    }
                } else {
                    if (response.code() == RestaurantConstants.HTTP_UNAUTHORIZED) {
                        String previousToken = DashPreferences.getAuthToken(LoginActivity.this);
                        if (previousToken != null) {
                            DashPreferences.setAuthToken(null, LoginActivity.this);
                            refreshToken(previousToken);
                        } else {
                            handleError(getString(R.string.login_error));
                        }
                    } else {
                        handleError(getString(R.string.login_error));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                handleError(getString(R.string.login_error));
            }
        });
    }

    private void refreshToken(String token) {
        RetrofitInterface request = mRetrofit.create(RetrofitInterface.class);
        Call<Token> call = request.refreshToken(RestaurantConstants.TOKEN_ID + token);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();

                    if (token != null && token.token != null) {
                        DashPreferences.setAuthToken(token.token, LoginActivity.this);
                        getUserData(token.token);
                    } else{
                        handleError(getString(R.string.login_error));
                    }
                } else {
                    if (response.code() == RestaurantConstants.HTTP_BAD_REQUEST) {
                        handleError(getString(R.string.login_wrong_credentials));
                    } else {
                        handleError(getString(R.string.login_error));
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                handleError(getString(R.string.login_error));
            }
        });
    }

    /** LISTENER METHODS _______________________________________________________________________ **/

    @Override
    public void onLoginButtonClicked() {
        getAuthToken();
    }

    @Override
    public void onSkipLoginButtonClicked() {
        launchMainActivity();
    }
}