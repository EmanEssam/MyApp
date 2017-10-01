package com.appswarrior.www.myapp.main.CountriesScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appswarrior.www.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eman Essam on 29/09/2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    List<String> countryList = new ArrayList<>();


    public CountryAdapter() {
    }

    public CountryAdapter(List<String> countryList) {

        this.countryList = countryList;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_row, parent, false);
        CountryViewHolder vh = new CountryViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {

        holder.country_name.setText(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }


    public class CountryViewHolder extends RecyclerView.ViewHolder {
        public TextView country_name;

        public CountryViewHolder(View itemView) {
            super(itemView);
            country_name = (TextView) itemView.findViewById(R.id.country_name);
        }
    }

    public void setFilter(List<String> countryModels) {

        countryList=countryModels;
        notifyDataSetChanged();
    }

}
