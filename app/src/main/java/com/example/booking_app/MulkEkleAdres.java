package com.example.booking_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class MulkEkleAdres extends Fragment {

    //region tanımlamalar
    View view;
    Context context;
    EditText edtTxtAdres;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    _Mulk mulk = null;
    boolean ekle_duzenle = false; // false:ekle - true:duzenle
    //endregion

    //region constructor
    public MulkEkleAdres() {
        this.ekle_duzenle = false;
    }

    public MulkEkleAdres(_Mulk mulk) {
        this.mulk = mulk;
        this.ekle_duzenle = true;
    }

    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //region tanımlamalar
        view = inflater.inflate(R.layout.fragment_mulk_ekle_adres, container, false);
        context = this.getActivity();
        edtTxtAdres = (EditText) view.findViewById(R.id.fragmentMulkEkleAdres_adres);

        sharedPref = context.getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        //endregion

        //region duzenleme modu
        if (ekle_duzenle && mulk != null) {
            edtTxtAdres.setText(mulk.getAdres());
            ekle_duzenle = false;
        }
        //endregion

        //region adres hata kontrol
        edtTxtAdres.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor.putString("adres", edtTxtAdres.getText().toString());
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //endregion

        return view;
    }
}
