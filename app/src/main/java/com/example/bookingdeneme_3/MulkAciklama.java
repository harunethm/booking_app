package com.example.bookingdeneme_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MulkAciklama extends Fragment {

    View view;
    TextView txtAciklama;
    String aciklama;

    public MulkAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mulk_aciklama, container, false);
        txtAciklama = view.findViewById(R.id.fragmentMulkAciklama_aciklama);
        txtAciklama.setText(aciklama);
        return view;
    }
}
