package com.example.booking_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Profil extends Fragment {

    //region tanımlamalar
    TextView adSoyad, telNo, eMail, sifre;
    ImageButton btnDuzenle;
    Button btnCikisYap;
    ImageView profilResmi;

    Fragment f = null;
    Context context;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    _veriKaynagi vk;
    _Kullanici k;
    //endregion


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        //region tanımlamalar
        context = this.getActivity();

        assert context != null;
        sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        spEditor = sp.edit();

        vk = new _veriKaynagi(context);
        k = vk.kullaniciSec();

        adSoyad = view.findViewById(R.id.fragmentProfil_txtAdSoyad);
        telNo = view.findViewById(R.id.fragmentProfil_txtTelNo);
        eMail = view.findViewById(R.id.fragmentProfil_txtEMail);
        sifre = view.findViewById(R.id.fragmentProfil_txtSifre);
        btnDuzenle = view.findViewById(R.id.fragmentProfil_ibDuzenle);
        btnCikisYap = view.findViewById(R.id.fragmentProfil_btnCikisYap);
        profilResmi = (ImageView) view.findViewById(R.id.fragmentProfil_profilResmi);
        //endregion

        //region setText
        if (k.getProfilResmi() != null)
            profilResmi.setImageBitmap(k.getProfilResmi());
        adSoyad.setText(k.getAdSoyad());
        telNo.setText(k.getTelNo());
        eMail.setText(k.geteMail());
        sifre.setText(k.getSifre());
        //endregion

        //region düzenle onClick
        btnDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spEditor.putInt("ID", k.getID());
                spEditor.apply();

                f = new ProfilDuzenle();
                getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
            }
        });
        //endregion

        //region çıkış yap onClick
        btnCikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vk.aktiflikGuncelle(0);
                Intent intent = new Intent(context, GirisEkrani.class);
                startActivity(intent);
            }
        });
        //endregion

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
