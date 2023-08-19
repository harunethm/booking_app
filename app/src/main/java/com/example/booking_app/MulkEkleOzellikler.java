package com.example.booking_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MulkEkleOzellikler extends Fragment {

    //region tanımlamalar
    View view;

    Context context;

    Spinner spGunlukAylik, spMulkTipi, spOdaSayisi, spKatNo, spKatSayisi, spEsyali, spIsitma, spCephe, spYalitim, spAsansor;
    List<Spinner> spinnerList;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    boolean degisim;

    _Mulk mulk = null;
    boolean ekle_duzenle = false; // false:ekle - true:duzenle
    //endregion

    //region constructor
    public MulkEkleOzellikler() {
        this.ekle_duzenle = false;
    }

    public MulkEkleOzellikler(_Mulk mulk) {
        this.mulk = mulk;
        this.ekle_duzenle = true;
    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //region tanımlamalar
        view = inflater.inflate(R.layout.fragment_mulk_ekle_ozellikler, container, false);

        context = this.getActivity();

        spGunlukAylik = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spGunlukAylik);
        spMulkTipi = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spMulkTipi);
        spOdaSayisi = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spOdaSayisi);
        spKatNo = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spKatNo);
        spKatSayisi = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spKatSayisi);
        spEsyali = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spEsyali);
        spIsitma = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spIsitma);
        spCephe = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spCephe);
        spYalitim = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spYalitim);
        spAsansor = (Spinner) view.findViewById(R.id.fragmentMulkEkleOzellikleri_spAsansor);

        spinnerList = new ArrayList<>();
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

        sharedPref = context.getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        degisim = false;
        //endregion

        //region senOnItemSelectedListener
        spGunlukAylik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spGunlukAylik.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("gunlukAylik", spGunlukAylik.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMulkTipi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spMulkTipi.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("mulkTipi", spMulkTipi.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spOdaSayisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spOdaSayisi.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("odaSayisi", spOdaSayisi.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spKatNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spKatNo.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("katNo", spKatNo.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spKatSayisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spKatSayisi.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("katSayisi", spKatSayisi.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spEsyali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spEsyali.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putInt("esyali", spEsyali.getSelectedItem().toString().equals("Var") ? 1 : 0);
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spIsitma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spIsitma.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("isitma", spIsitma.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCephe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spCephe.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putString("cephe", spCephe.getSelectedItem().toString());
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spYalitim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spYalitim.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putInt("yalitim", spYalitim.getSelectedItem().toString().equals("Var") ? 1 : 0);
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spAsansor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spAsansor.getSelectedItem().toString().contains("Seçiniz")) {
                    editor.putInt("asansor", spAsansor.getSelectedItem().toString().equals("Var") ? 1 : 0);
                    editor.apply();
                    degisim = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region duzenleme modu
        if (ekle_duzenle && mulk != null) {

            List<String> mulkVeri = new ArrayList<>();
            mulkVeri.add(mulk.getGunlukAylik());
            mulkVeri.add(mulk.getMulkTipi());
            mulkVeri.add(mulk.getOdaSayisi());
            mulkVeri.add(mulk.getKatNo());
            mulkVeri.add(mulk.getKatSayisi());
            mulkVeri.add(mulk.getEsyali() == 1 ? "Var" : "Yok");
            mulkVeri.add(mulk.getIsitma());
            mulkVeri.add(mulk.getCephe());
            mulkVeri.add(mulk.getYalitim() == 1 ? "Var" : "Yok");
            mulkVeri.add(mulk.getAsansor() == 1 ? "Var" : "Yok");

            for (int i = 0; i < spinnerList.size(); i++){
                for (int j = 0; j < spinnerList.get(i).getCount(); j++){
                    if (spinnerList.get(i).getItemAtPosition(j).equals(mulkVeri.get(i)))
                        spinnerList.get(i).setSelection(j);
                }
            }

            ekle_duzenle = false;
        }
        //endregion

        //region hata kontrol
        editor.putBoolean("ozellikHata", false);
        if (degisim) {
            for (Spinner sp : spinnerList) {
                if (sp.getSelectedItem().toString().toLowerCase().contains("seçiniz")) {
                    Toast.makeText(context, "" + sp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    editor.putBoolean("ozellikHata", true);
                    break;
                }
            }
        }
        editor.apply();
        //endregion

        return view;
    }
}