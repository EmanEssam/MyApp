package com.appswarrior.www.myapp.register;

import android.content.Context;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public interface RegisterInteractor {

    interface OnRegisterFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onMobileError();

        void onNameError();

        void onMailError();

        void onRegisterSuccess();

        void onRegisterFailed();
    }

    void register(String name, String username, String mail, String password, String mobile, int gender, OnRegisterFinishedListener listener);
}
