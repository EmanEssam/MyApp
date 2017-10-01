package com.appswarrior.www.myapp.main.CountriesScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.appswarrior.www.myapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements SearchView.OnQueryTextListener, CountryView {
    private RecyclerView mCountryList;
    private RecyclerView.LayoutManager layoutManager;
    private CountryAdapter mCountryAdapter;
    List<String> list = new ArrayList<>();
    private SearchView mSearchView;
    private CountryPresenter countryPresenter = new CountryPresenterImpl(this);


    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        countryPresenter.getCountries();
        mCountryList = (RecyclerView) view.findViewById(R.id.country_rv);
        layoutManager = new LinearLayoutManager(getActivity());
        mCountryList.setLayoutManager(layoutManager);
        mSearchView = (SearchView) view.findViewById(R.id.country_searchview);
        mSearchView.setOnQueryTextListener(this);
        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<String> filteredModelList = filter(list, newText);
        if (filteredModelList.size() > 0) {
            mCountryAdapter.setFilter(filteredModelList);
            mCountryAdapter.notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    private List<String> filter(List<String> models, String query) {
        query = query.toLowerCase();
        final List<String> filteredModelList = new ArrayList<>();
        for (String model : models) {
            if (model.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public void getCountries(List<String> countryList) {
        list = countryList;
        mCountryAdapter = new CountryAdapter(countryList);
        mCountryList.setAdapter(mCountryAdapter);
    }

    @Override
    public void getCountryError(String errorMessage) {
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();

    }
}
