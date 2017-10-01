package com.appswarrior.www.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eman Essam on 30/09/2017.
 */

public class PreferencesManager {

    private static final String PREF_NAME = "info";
    private static final String NAME_KEY_VALUE = "name";
    private static final String PHONE_KEY_VALUE = "phone";
    private static final String LOGIN_KEY_VALUE = "login";


    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setLogin(Boolean logged_in) {
        mPref.edit()
                .putBoolean(LOGIN_KEY_VALUE, logged_in)
                .apply();
    }

    public void setValue(String name, String phone) {
        mPref.edit()
                .putString(NAME_KEY_VALUE, name)
                .putString(PHONE_KEY_VALUE, phone)
                .apply();
    }

    public boolean getLoggedIn() {
        return mPref.getBoolean(LOGIN_KEY_VALUE, false);
    }

    public String getName() {
        return mPref.getString(NAME_KEY_VALUE, "");
    }

    public String getPhone() {
        return mPref.getString(PHONE_KEY_VALUE, "");
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .apply();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}