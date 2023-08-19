package com.example.bookingdeneme_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MulkOzellikler extends Fragment {

    //region tanımlamalar
    View view;

    TextView gunlukAylik, mulkTipi, odaSayisi, katSayisi, isitma, cephe, katNo, esyali, yalitim, asansor, ucret;

    _Mulk mulk = null;
    //endregion

    //region constructor
    public MulkOzellikler(_Mulk mulk) {
        this.mulk = mulk;
    }
    //endregion

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //region tanımlamalar
        view = inflater.inflate(R.layout.fragment_mulk_ozellikler, container, false);

        ucret = view.findViewById(R.id.fragmentMulkOzellikler_tvUcret);
        gunlukAylik = view.findViewById(R.id.fragmentMulkOzellikler_tvGunlukAylik);
        mulkTipi = view.findViewById(R.id.fragmentMulkOzellikler_tvMulkTipi);
        odaSayisi = view.findViewById(R.id.fragmentMulkOzellikler_tvOdaSayisi);
        katNo = view.findViewById(R.id.fragmentMulkOzellikler_tvKatNo);
        katSayisi = view.findViewById(R.id.fragmentMulkOzellikler_tvKatSayisi);
        esyali = view.findViewById(R.id.fragmentMulkOzellikler_tvEsyali);
        isitma = view.findViewById(R.id.fragmentMulkOzellikler_tvIsitma);
        cephe = view.findViewById(R.id.fragmentMulkOzellikler_tvCephe);
        yalitim = view.findViewById(R.id.fragmentMulkOzellikler_tvYalitim);
        asansor = view.findViewById(R.id.fragmentMulkOzellikler_tvAsansor);
        //endregion

        //region setText

        ucret.setText(mulk.getUcret().toString());
        gunlukAylik.setText(mulk.getGunlukAylik());
        mulkTipi.setText(mulk.getMulkTipi());
        odaSayisi.setText(mulk.getOdaSayisi());
        katNo.setText(mulk.getKatNo());
        katSayisi.setText(mulk.getKatSayisi());
        esyali.setText(mulk.getEsyali() == 1 ? "Var" : "Yok");
        isitma.setText(mulk.getIsitma());
        cephe.setText(mulk.getCephe());
        yalitim.setText(mulk.getYalitim() == 1 ? "Var" : "Yok");
        asansor.setText(mulk.getAsansor() == 1 ? "Var" : "Yok");

        //endregion

        return view;
    }


}
