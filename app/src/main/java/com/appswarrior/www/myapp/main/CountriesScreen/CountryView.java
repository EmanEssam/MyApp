package com.appswarrior.www.myapp.main.CountriesScreen;

import java.util.List;

/**
 * Created by Eman Essam on 01/10/2017.
 */

public interface CountryView {
    void getCountries(List<String> countryList);
    void getCountryError(String errorMessage);
}
