package com.appswarrior.www.myapp.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appswarrior.www.myapp.R;
import com.appswarrior.www.myapp.main.MainActivity;
import com.appswarrior.www.myapp.register.RegistrationActivity;
import com.appswarrior.www.myapp.utils.ConnectivityReceiver;
import com.appswarrior.www.myapp.utils.MyApplication;
import com.appswarrior.www.myapp.utils.PreferencesManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginActivity extends FragmentActivity implements LoginView, View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {
    private EditText mUsername, mPassword;
    private TextView mLoginBtn, mCreateAccount;
    private ProgressBar mLoginLoader;
    private LoginPresenter mLoginPresenter;
    private LoginButton mFbLoginBtn;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkConnection();
        final PreferencesManager preferencesManager = PreferencesManager.getInstance();
        mLoginPresenter = new LoginPresenterImpl(this);
        mUsername = (EditText) findViewById(R.id.usernameEt);
        mPassword = (EditText) findViewById(R.id.passwordEt);
        mLoginBtn = (TextView) findViewById(R.id.login);
        mLoginLoader = (ProgressBar) findViewById(R.id.login_loader);
        mCreateAccount = (TextView) findViewById(R.id.signup);
        mFbLoginBtn = (LoginButton) findViewById(R.id.fb_login_button);
        mLoginBtn.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);
        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        mFbLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                preferencesManager.setLogin(true);
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    preferencesManager.setValue(profile.getName(), "");
                }
                navigateToHome();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                showLoginErrorMessage();
            }

        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            mLoginPresenter.validateCredentials(mUsername.getText().toString(), mPassword.getText().toString());
        } else if (v.getId() == R.id.signup) {
            navigateToRegisterScreen();
        }
    }

    @Override
    public void showProgress() {
        mLoginLoader.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mLoginLoader.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        mUsername.setError(getString(R.string.require_field));
    }

    @Override
    public void setPasswordError() {
        mPassword.setError(getString(R.string.require_field));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    @Override
    public void navigateToRegisterScreen() {
        startActivity(new Intent(this, RegistrationActivity.class));
        finish();
    }

    @Override
    public void showLoginErrorMessage() {
        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

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
                .make(findViewById(R.id.activity_login), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
}
