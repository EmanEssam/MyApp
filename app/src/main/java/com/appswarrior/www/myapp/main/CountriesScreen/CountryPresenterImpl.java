package com.appswarrior.www.myapp.main.CountriesScreen;

import java.util.List;


/**
 * Created by Eman Essam on 01/10/2017.
 */

public class CountryPresenterImpl implements CountryPresenter, CountryInteractor.onGetCountriesFinished {
    CountryView countryView;
    CountryInteractor countryInteractor;

    public CountryPresenterImpl(CountryView countryView) {
        this.countryView = countryView;
        this.countryInteractor = new CountryInteractorImpl() ;
    }

    @Override
    public void onGetCountriesSuccess(List<String> countryList) {
        countryView.getCountries(countryList);

    }

    @Override
    public void onGetCountriesFailed(String message) {
        countryView.getCountryError(message);

    }

    @Override
    public void getCountries() {
        countryInteractor.getCountries(this);
    }
}
