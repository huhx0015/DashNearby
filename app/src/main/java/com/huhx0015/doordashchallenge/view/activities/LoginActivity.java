package com.huhx0015.doordashchallenge.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.huhx0015.doordashchallenge.R;
import com.huhx0015.doordashchallenge.api.RetrofitInterface;
import com.huhx0015.doordashchallenge.application.RestaurantApplication;
import com.huhx0015.doordashchallenge.constants.RestaurantConstants;
import com.huhx0015.doordashchallenge.databinding.ActivityLoginBinding;
import com.huhx0015.doordashchallenge.models.Login;
import com.huhx0015.doordashchallenge.models.Restaurant;
import com.huhx0015.doordashchallenge.models.Token;
import com.huhx0015.doordashchallenge.viewmodels.LoginActivityViewModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michael Yoon Huh on 6/7/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityViewModel.LoginActivityViewModelListener {

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

    @Override
    public void onLoginButtonClicked() {
        userLogin();
    }

    private void userLogin() {

        final RetrofitInterface request = mRetrofit.create(RetrofitInterface.class);

        Call<Token> call = request.queryLoginToken(new Login(mEmail, mPassword));

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {

                    Token token = response.body();
                    if (token != null) {
                        // TODO: Store token
                    } else{
                        // TODO: Handle error.
                        //Toast.makeText(this, "ERROR: No token", Toast.LENGTH_SHORT)
                    }
                } else {
                    // TODO: Handle error
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                // TODO: Handle error.
            }
        });
    }
}
