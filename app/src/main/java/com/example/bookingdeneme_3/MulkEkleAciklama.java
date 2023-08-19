package com.example.bookingdeneme_3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class MulkEkleAciklama extends Fragment {

    //region tanımlamalar
    EditText edtTxtAciklama;
    View view;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    _Mulk mulk = null;
    boolean ekle_duzenle = false; // false:ekle - true:duzenle
    //endregion

    //region constructor
    public MulkEkleAciklama() {
        this.ekle_duzenle = false;
    }

    public MulkEkleAciklama(_Mulk mulk) {
        this.mulk = mulk;
        this.ekle_duzenle = true;
    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //region tanımlamalar
        view = inflater.inflate(R.layout.fragment_mulk_ekle_aciklama, container, false);
        sharedPref = this.getActivity().getSharedPreferences("sharedpref", this.getActivity().MODE_PRIVATE);
        editor = sharedPref.edit();
        edtTxtAciklama = view.findViewById(R.id.fragmentMulkEkleAciklama_aciklama);
        //endregion

        //region aciklama textChanged
        edtTxtAciklama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("aciklama", edtTxtAciklama.getText().toString());
                editor.apply();
            }
        });
        //endregion

        //region duzenleme modu
        if (ekle_duzenle && mulk != null) {
            edtTxtAciklama.setText(mulk.getAciklama());
            ekle_duzenle = false;
        }
        //endregion

        return view;
    }
}
