package com.appswarrior.www.myapp.main.ContactScreen;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appswarrior.www.myapp.R;
import com.appswarrior.www.myapp.utils.PreferencesManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    TextView mUserName, mUserPhone;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserPhone = (TextView) view.findViewById(R.id.user_phone);
        mUserPhone.setText(getString(R.string.phone_string) + preferencesManager.getPhone());
        mUserName.setText(getString(R.string.name_string) + preferencesManager.getName());
        return view;

    }
}
