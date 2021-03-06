package com.appswarrior.www.myapp.main.CountriesScreen;

import java.util.List;

/**
 * Created by Eman Essam on 01/10/2017.
 */

public interface CountryInteractor {

    interface onGetCountriesFinished {

        void onGetCountriesSuccess(List<String> countryList);

        void onGetCountriesFailed(String message);

    }

    void getCountries(onGetCountriesFinished onGetCountriesFinished);
}
