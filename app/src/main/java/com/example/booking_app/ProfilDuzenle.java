package com.example.booking_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Images.Media.getBitmap;

public class ProfilDuzenle extends Fragment {

    //region tanımlamalar
    private final static int resimSec = 1;
    private Bitmap bitmap;

    TextInputLayout tilAdSoyad, tilTelNo, tilEMail, tilSifre;
    EditText edtTxtAdSoyad, edtTxtTelNo, edtTxtEMail, edtTxtSifre;
    ImageButton ibProfilResmi, ibCikis;
    Button btnKaydet;
    Spinner spEMail;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    _veriKaynagi vk;
    BilgiKontrol bk;

    _Kullanici k = null;
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_duzenle, container, false);

        //region tanımlamalar
        vk = new _veriKaynagi(this.getActivity());
        bk = new BilgiKontrol(this.getActivity());

        sp = this.requireActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);

        tilAdSoyad = view.findViewById(R.id.fragmentProfilDuzenle_tilAdSoyad);
        tilTelNo = view.findViewById(R.id.fragmentProfilDuzenle_tilTelNo);
        tilEMail = view.findViewById(R.id.fragmentProfilDuzenle_tilEMail);
        tilSifre = view.findViewById(R.id.fragmentProfilDuzenle_tilSifre);

        edtTxtAdSoyad = (EditText) view.findViewById(R.id.fragmentProfilDuzenle_edtTxtAdsoyad);
        edtTxtTelNo = (EditText) view.findViewById(R.id.fragmentProfilDuzenle_edtTxtTelNo);
        edtTxtEMail = (EditText) view.findViewById(R.id.fragmentProfilDuzenle_edtTxtEMail);
        edtTxtSifre = (EditText) view.findViewById(R.id.fragmentProfilDuzenle_edtTxtSifre);

        spEMail = (Spinner) view.findViewById(R.id.fragmenKayitOl_spEMail);

        ibProfilResmi = (ImageButton) view.findViewById(R.id.fragmentProfilDuzenle_ibProfilResmi);
        ibCikis = view.findViewById(R.id.fragmentProfilDuzenle_ibCikis);

        btnKaydet = view.findViewById(R.id.fragmentProfilDuzenle_btnKaydet);

        k = vk.kullaniciSec();
        //endregion

        //region setText
        if (k.getProfilResmi() != null)
            ibProfilResmi.setImageBitmap(k.getProfilResmi());

        edtTxtAdSoyad.setText(k.getAdSoyad());
        edtTxtTelNo.setText(k.getTelNo());

        edtTxtEMail.setText(k.geteMail().split("@")[0]);
        switch (k.geteMail().split("@")[1]) {
            case "gmail.com":
                spEMail.setSelection(0);
                break;
            case "hotmail.com":
                spEMail.setSelection(1);
                break;
            case "outlook.com":
                spEMail.setSelection(2);
                break;
            default:
                break;
        }
        edtTxtSifre.setText(k.getSifre());
        //endregion

        //region kaydet onClick
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region kontroller
                boolean hata = false;
                if (k.getProfilResmi() != bitmap) {
                    k.setProfilResmi(bitmap);
                }

                if (!k.getAdSoyad().equals(edtTxtAdSoyad.getText().toString()) && !bk.adSoyadKontrol(edtTxtAdSoyad, tilAdSoyad)) {
                    k.setAdSoyad(edtTxtAdSoyad.getText().toString());
                } else if (!k.getAdSoyad().equals(edtTxtAdSoyad.getText().toString()) && bk.adSoyadKontrol(edtTxtAdSoyad, tilAdSoyad)) {
                    hata = true;
                }

                if (!k.getTelNo().equals(edtTxtTelNo.getText().toString()) && !bk.telNoKontrol(edtTxtTelNo, tilTelNo, k.getID())) {
                    k.setTelNo(edtTxtTelNo.getText().toString());
                } else if (!k.getTelNo().equals(edtTxtTelNo.getText().toString()) && bk.telNoKontrol(edtTxtTelNo, tilTelNo, k.getID())) {
                    hata = true;
                }

                if (!k.geteMail().equals(edtTxtEMail.getText().toString()) && !bk.mailKontrol(edtTxtEMail, tilEMail, spEMail, k.getID())) {
                    k.seteMail(edtTxtEMail.getText().toString() + "" + spEMail.getSelectedItem().toString());
                } else if (!k.geteMail().equals(edtTxtEMail.getText().toString()) && bk.mailKontrol(edtTxtEMail, tilEMail, spEMail, k.getID())) {
                    hata = true;
                }

                if (!k.sifre.equals(edtTxtSifre.getText().toString()) && !bk.sifreKontrol(edtTxtSifre, tilSifre)) {
                    k.setSifre(edtTxtSifre.getText().toString());
                } else if (!k.getSifre().equals(edtTxtSifre.getText().toString()) && bk.sifreKontrol(edtTxtSifre, tilSifre)) {
                    hata = true;
                }

                if (!hata) {
                    vk.kisiGuncelle(k);
                    Fragment f = new Profil();
                    getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
                }
                //endregion
            }
        });
        //endregion

        //region çıkış yap onClick
        ibCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new Profil();
                getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
            }
        });
        //endregion

        //region profil resmi onClick
        ibProfilResmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Resim Seç"), resimSec);
            }
        });
        //endregion

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == resimSec && resultCode == RESULT_OK && data != null) {
                this.bitmap = getBitmap(this.requireActivity().getContentResolver(), data.getData());
                ibProfilResmi.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}