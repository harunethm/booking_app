package com.example.booking_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MulkAdres extends Fragment {

    View view;
    TextView txtAdres;
    String adres;

    public MulkAdres(String adres) {
        this.adres = adres;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mulk_adres, container, false);
        txtAdres = view.findViewById(R.id.fragmentMulkAdres_adres);
        txtAdres.setText(adres);
        return view;
    }
}
