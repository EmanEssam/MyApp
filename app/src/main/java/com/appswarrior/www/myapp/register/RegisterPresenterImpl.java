package com.appswarrior.www.myapp.register;

import android.content.Context;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnRegisterFinishedListener {
    private RegisterView registerView;
    private RegisterInteractor registerInteractor;
    private Context context;

    public RegisterPresenterImpl(RegisterView registerView, Context context) {
        this.registerView = registerView;
        this.registerInteractor = new RegisterInteractorImpl();
        this.context = context;
    }

    @Override
    public void validateCredentials(String name, String username, String mail, String password, String mobile) {

        if (registerView != null) {
            registerView.showProgress();
        }

        registerInteractor.register(name, username, mail, password, mobile, 1, this);

    }

    @Override
    public void onUsernameError() {
        if (registerView != null) {
            registerView.setUsernameError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onPasswordError() {
        if (registerView != null) {
            registerView.setPasswordError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onMobileError() {
        if (registerView != null) {
            registerView.setMobileError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onNameError() {
        if (registerView != null) {
            registerView.setNameError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onMailError() {
        if (registerView != null) {
            registerView.setMailError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onRegisterSuccess() {
        if (registerView != null) {
            registerView.hideProgress();
            registerView.navigateToLogin();

        }
    }

    @Override
    public void onRegisterFailed() {
        if (registerView != null) {
            registerView.hideProgress();
            registerView.showRegisterErrorMessage();
        }
    }
}
