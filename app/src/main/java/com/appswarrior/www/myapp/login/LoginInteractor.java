package com.appswarrior.www.myapp.login;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public interface LoginInteractor {
    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onLoginSuccess();

        void onLoginFailed();

    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
