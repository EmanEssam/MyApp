package com.appswarrior.www.myapp.main.CountriesScreen;

import com.appswarrior.www.myapp.model.CountryResponse;
import com.appswarrior.www.myapp.service.ApiHelper;
import com.appswarrior.www.myapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eman Essam on 01/10/2017.
 */

public class CountryInteractorImpl implements CountryInteractor {
    onGetCountriesFinished onGetCountriesFinished;
    List<String> list = new ArrayList<>();

    @Override
    public void getCountries(final onGetCountriesFinished onGetCountriesFinished) {
        this.onGetCountriesFinished = onGetCountriesFinished;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.COUNTRY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiHelper service = retrofit.create(ApiHelper.class);
        Call<List<CountryResponse>> call = service.getCountryList();

        call.enqueue(new Callback<List<CountryResponse>>() {

            @Override
            public void onResponse(Call<List<CountryResponse>> call, Response<List<CountryResponse>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    list.add(response.body().get(i).getCapital());
                }
                onGetCountriesFinished.onGetCountriesSuccess(list);

            }


            @Override
            public void onFailure(Call<List<CountryResponse>> call, Throwable t) {
                onGetCountriesFinished.onGetCountriesFailed(t.getMessage());

            }
        });
    }
}
