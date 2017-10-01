package com.appswarrior.www.myapp.login;

import android.text.TextUtils;

import com.appswarrior.www.myapp.model.LoginResponse;
import com.appswarrior.www.myapp.service.ApiHelper;
import com.appswarrior.www.myapp.utils.Constants;
import com.appswarrior.www.myapp.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    OnLoginFinishedListener onLoginFinishedListener;
    PreferencesManager preferencesManager = PreferencesManager.getInstance();


    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener onLoginFinishedListener) {
        this.onLoginFinishedListener = onLoginFinishedListener;
        if (TextUtils.isEmpty(username)) {
            onLoginFinishedListener.onUsernameError();
        } else if (TextUtils.isEmpty(password)) {
            onLoginFinishedListener.onPasswordError();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiHelper service = retrofit.create(ApiHelper.class);
            Call<LoginResponse> call = service.login(username, password);

            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.body().getSuccess().equals("ok")) {
                        preferencesManager.setLogin(true);
                        onLoginFinishedListener.onLoginSuccess();

                    } else {
                        onLoginFinishedListener.onLoginFailed();
                    }
                }


                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    onLoginFinishedListener.onLoginFailed();

                }
            });
        }

    }


}
