package com.appswarrior.www.myapp.login;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void navigateToRegisterScreen();

    void showLoginErrorMessage();
}
