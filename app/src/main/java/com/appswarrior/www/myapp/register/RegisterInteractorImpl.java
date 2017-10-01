package com.appswarrior.www.myapp.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.appswarrior.www.myapp.service.ApiHelper;
import com.appswarrior.www.myapp.utils.Constants;
import com.appswarrior.www.myapp.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    OnRegisterFinishedListener onregisterFinishedListener;

    @Override
    public void register( final String name, final String username, String mail, final String password, final String mobile, int gender, final OnRegisterFinishedListener onregisterFinishedListener) {
        this.onregisterFinishedListener = onregisterFinishedListener;
        if (TextUtils.isEmpty(name)) {
            onregisterFinishedListener.onNameError();
        } else if (TextUtils.isEmpty(username)) {
            onregisterFinishedListener.onUsernameError();
        } else if (TextUtils.isEmpty(mail)) {
            onregisterFinishedListener.onMailError();
        } else if (TextUtils.isEmpty(password)) {
            onregisterFinishedListener.onPasswordError();
        } else if (TextUtils.isEmpty(mobile)) {
            onregisterFinishedListener.onMobileError();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiHelper service = retrofit.create(ApiHelper.class);
            Call<RegisterResponse> call = service.register(name, username, mail, password, mobile, gender);

            call.enqueue(new Callback<RegisterResponse>() {

                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    PreferencesManager preferencesManager = PreferencesManager.getInstance();
                    preferencesManager.setValue(username, mobile);
                    onregisterFinishedListener.onRegisterSuccess();

                }


                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    onregisterFinishedListener.onRegisterFailed();
                }
            });

        }

    }
}
