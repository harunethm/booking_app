package com.example.booking_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MulkFiltrele extends Fragment {

    //region tanımlamalar
    View view;
    Context context;
    FloatingActionButton fabFiltrele;
    EditText edtTxtAdres, edtTxtMinUcret, edtTxtMaxUcret;
    Spinner spGunlukAylik, spMulkTipi, spOdaSayisi, spKatNo, spKatSayisi, spEsyali, spIsitma, spCephe, spYalitim, spAsansor;
    int a;

    //endregion
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //region tanımlamalar
        view = inflater.inflate(R.layout.fragment_filtrele, container, false);
        context = this.getActivity();

        fabFiltrele = (FloatingActionButton) view.findViewById(R.id.fragmentFiltrele_fabFiltrele);

        spGunlukAylik = (Spinner) view.findViewById(R.id.fragmentFiltrele_spGunlukAylik);
        spMulkTipi = (Spinner) view.findViewById(R.id.fragmentFiltrele_spMulkTipi);
        spOdaSayisi = (Spinner) view.findViewById(R.id.fragmentFiltrele_spOdaSayisi);
        spKatNo = (Spinner) view.findViewById(R.id.fragmentFiltrele_spKatNo);
        spKatSayisi = (Spinner) view.findViewById(R.id.fragmentFiltrele_spKatSayisi);
        spEsyali = (Spinner) view.findViewById(R.id.fragmentFiltrele_spEsyali);
        spIsitma = (Spinner) view.findViewById(R.id.fragmentFiltrele_spIsitma);
        spCephe = (Spinner) view.findViewById(R.id.fragmentFiltrele_spCephe);
        spYalitim = (Spinner) view.findViewById(R.id.fragmentFiltrele_spYalitim);
        spAsansor = (Spinner) view.findViewById(R.id.fragmentFiltrele_spAsansor);

        edtTxtAdres = (EditText) view.findViewById(R.id.fragmentFiltrele_edtTxtAdres);
        edtTxtMinUcret = (EditText) view.findViewById(R.id.fragmentFiltrele_edtTxtMinUcret);
        edtTxtMaxUcret = (EditText) view.findViewById(R.id.fragmentFiltrele_edtTxtMaxUcret);

        fabFiltrele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adres = edtTxtAdres.getText().toString();
                String min = edtTxtMinUcret.getText().toString();
                String max = edtTxtMaxUcret.getText().toString();

                List<Spinner> spinnerList = new ArrayList<>();
                spinnerList.add(spGunlukAylik);
                spinnerList.add(spMulkTipi);
                spinnerList.add(spOdaSayisi);
                spinnerList.add(spKatNo);
                spinnerList.add(spKatSayisi);
                spinnerList.add(spEsyali);
                spinnerList.add(spIsitma);
                spinnerList.add(spCephe);
                spinnerList.add(spYalitim);
                spinnerList.add(spAsansor);

                Fragment f = new MevcutMulkler(spinnerList, min, max, adres);
                getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
            }
        });
        //endregion
        return view;
    }
}
