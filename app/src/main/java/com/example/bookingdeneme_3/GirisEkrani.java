package com.example.bookingdeneme_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class GirisEkrani extends AppCompatActivity {


    //region tanımlamalar
    TextInputLayout tilEMail, tilSifre;
    EditText edtTxtKullaniciAdi, edtTxtSifre;

    Intent intent = null;
    _veriKaynagi vk;
    List<_Kullanici> kullanicilar = null;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region tanımlamalar
        setContentView(R.layout.giris_ekrani);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

        tilEMail = (TextInputLayout) findViewById(R.id.girisEkrani_tilEMail);
        tilSifre = (TextInputLayout) findViewById(R.id.girisEkrani_tilSifre);

        edtTxtKullaniciAdi = (EditText) findViewById(R.id.girisEkrani_edtTxtKullaniciAdi);
        edtTxtSifre = (EditText) findViewById(R.id.girisEkrani_edtTxtSifre);

        vk = new _veriKaynagi(this);
        kullanicilar = vk.kullanicilariListele();
        //endregion

        //region  açık kullanıcı var ise direk olarak mainActivity ye yönlendiren kısım
        for (_Kullanici k : kullanicilar) {
            if (k.getAktifMi() == 1) {
                intent = new Intent(GirisEkrani.this, MainActivity.class);
                _veriKaynagi._id = k.getID();
                startActivity(intent);
            }
        }
        //endregion
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.girisEkrani_btnSifremiUnuttum:
                intent = new Intent(this, SifremiUnuttum.class);
                startActivity(intent);
                break;
            case R.id.girisEkrani_btnKayitOl:
                intent = new Intent(this, KayitEkrani.class);
                intent.putExtra("mail", edtTxtKullaniciAdi.getText().toString());
                intent.putExtra("sifre", edtTxtSifre.getText().toString());
                startActivity(intent);
                break;
            case R.id.girisEkrani_btnGirisYap:
                if (!mailSifreKontrolu(edtTxtKullaniciAdi.getText().toString(), edtTxtSifre.getText().toString())) {
                    intent = new Intent(this, MainActivity.class);
                    _veriKaynagi._id = vk.kullaniciID("eMail = ?", new String[]{edtTxtKullaniciAdi.getText().toString()});
                    vk.aktiflikGuncelle(1);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean mailSifreKontrolu(String eMail, String sifre) {
        boolean check = false;
        _Kullanici kullanici = null;
        for (_Kullanici k : kullanicilar) {
            if (k.geteMail().equals(eMail)) {
                kullanici = k;
                check = true;
                break;
            }
        }
        if (check) {
            if (kullanici.getSifre().equals(sifre)) {
                return false;
            } else {
                tilSifre.setError("Şifre yanlış.");
                return true;
            }
        } else {
            tilEMail.setError("Mail adresi kayıtlı değil.");
            return true;
        }
    }
}
