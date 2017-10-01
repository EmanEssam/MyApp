package com.appswarrior.www.myapp.register;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appswarrior.www.myapp.R;
import com.appswarrior.www.myapp.login.LoginActivity;
import com.appswarrior.www.myapp.utils.ConnectivityReceiver;
import com.appswarrior.www.myapp.utils.MyApplication;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, RegisterView ,ConnectivityReceiver.ConnectivityReceiverListener{
    private EditText mName, mUsername, mMail, mMobile, mPassword;
    private TextView mSignUpBtn, mLogin;
    private ProgressBar mSignUpLoader;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        registerPresenter = new RegisterPresenterImpl(this,this);
        mSignUpBtn.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                registerPresenter.validateCredentials(mName.getText().toString(), mUsername.getText().toString(), mMail.getText().toString(), mPassword.getText().toString(), mMobile.getText().toString());
                break;
            case R.id.login:
                navigateToLogin();
                break;
            default:
                break;

        }
    }

    @Override
    public void initViews() {
        mName = (EditText) findViewById(R.id.name_et);
        mUsername = (EditText) findViewById(R.id.username_et);
        mMail = (EditText) findViewById(R.id.email_et);
        mMobile = (EditText) findViewById(R.id.mob_et);
        mPassword = (EditText) findViewById(R.id.password_et);
        mSignUpBtn = (TextView) findViewById(R.id.signup);
        mLogin = (TextView) findViewById(R.id.login);
        mSignUpLoader = (ProgressBar) findViewById(R.id.signup_loader);
    }

    @Override
    public void showProgress() {
        mSignUpLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mSignUpLoader.setVisibility(View.GONE);

    }

    @Override
    public void setUsernameError() {
        mUsername.setError(getString(R.string.require_field));
    }

    @Override
    public void setMobileError() {
        mMobile.setError(getString(R.string.require_field));
    }

    @Override
    public void setNameError() {
        mName.setError(getString(R.string.require_field));
    }

    @Override
    public void setMailError() {
        mMail.setError(getString(R.string.require_field));
    }

    @Override
    public void setPasswordError() {
        mPassword.setError(getString(R.string.require_field));
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }

    @Override
    public void showRegisterErrorMessage() {
        Toast.makeText(this, R.string.register_failed, Toast.LENGTH_SHORT).show();
    }
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = getString(R.string.connected);
            color = Color.WHITE;
        } else {
            message = getString(R.string.not_connected);
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.activity_registration), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
}
