package com.appswarrior.www.myapp.register;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public interface RegisterView {

    void initViews();
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setMobileError();

    void setNameError();

    void setMailError();


    void setPasswordError();

    void navigateToLogin();

    void showRegisterErrorMessage();
}
