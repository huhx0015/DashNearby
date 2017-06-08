package com.huhx0015.doordashchallenge.view.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.RestaurantPreferences;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.application.RestaurantApplication;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.databinding.ActivityLoginBinding;
import com.huhx0015.doordashchallenge.models.Login;
import com.huhx0015.doordashchallenge.models.Token;
import com.huhx0015.doordashchallenge.models.User;
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

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    private ActivityLoginBinding mBinding;
    private LoginActivityViewModel mViewModel;

    private String mEmail = "";
    private String mPassword = "";

    @Inject
    Retrofit mRetrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RestaurantApplication) getApplication()).getNetworkComponent().inject(this);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mViewModel = new LoginActivityViewModel();
        mViewModel.setListener(this);
        mBinding.setViewModel(mViewModel);

        initTextWatchers();

        initLoginState();
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

        String token = RestaurantPreferences.getAuthToken(this);
        if (token != null) {
            getUserData(token);
        }
    }

    @Override
    public void onLoginButtonClicked() {
        getAuthToken();
    }

    private void getAuthToken() {
        Log.d(LOG_TAG, "getAuthToken(): Email: " + mEmail + " | mPassword: " + mPassword);

        RetrofitInterface request = mRetrofit.create(RetrofitInterface.class);
        Call<Token> call = request.getAuthToken(new Login(mEmail, mPassword));
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();

                    if (token != null && token.token != null) {
                        RestaurantPreferences.setAuthToken(token.token, LoginActivity.this);
                        getUserData(token.token);
                    } else{
                        Toast.makeText(LoginActivity.this, "An error occurred while trying to login. Please try again.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == RestaurantConstants.HTTP_BAD_REQUEST) {
                        Toast.makeText(LoginActivity.this, "Invalid credentials provided.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "An error occurred while trying to login. Please try again.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred while trying to login. Please try again.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUserData(String token) {
        RetrofitInterface request = mRetrofit.create(RetrofitInterface.class);
        Call<User> call = request.getUser(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                launchMainActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
