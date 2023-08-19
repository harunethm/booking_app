package com.example.bookingdeneme_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class KayitEkrani extends AppCompatActivity {

    //region tanımlamalar
    EditText edtTxtAdSoyad, edtTxtTelNo, edtTxtEMail, edtTxtSifre, edtTxtSifreTekrar;
    TextInputLayout tilAdSoyad, tilTelNo, tilEMail, tilSifre, tilSifreTekrar;
    Spinner spEMail;
    Button btnKayitOl;

    BilgiKontrol bk;
    _veriKaynagi vk;

    Context context;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // region tanımlamalar
        setContentView(R.layout.kayit_ekrani);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

        bk = new BilgiKontrol(this);
        vk = new _veriKaynagi(this);

        context = this;

        tilAdSoyad = findViewById(R.id.kayitEkrani_tilAdSoyad);
        tilTelNo = findViewById(R.id.kayitEkrani_tilTelNo);
        tilEMail = findViewById(R.id.kayitEkrani_tilEMail);
        tilSifre = findViewById(R.id.kayitEkrani_tilSifre);
        tilSifreTekrar = findViewById(R.id.kayitEkrani_tilSifreTekrar);

        edtTxtAdSoyad = (EditText) findViewById(R.id.kayitEkrani_edtTxtAdSoyad);
        edtTxtTelNo = (EditText) findViewById(R.id.kayitEkrani_edtTxtTelNo);
        edtTxtEMail = (EditText) findViewById(R.id.kayitEkrani_edtTxtEMail);
        edtTxtSifre = (EditText) findViewById(R.id.kayitEkrani_edtTxtSifre);
        edtTxtSifreTekrar = (EditText) findViewById(R.id.kayitEkrani_edtTxtSifreTekrar);

        spEMail = (Spinner) findViewById(R.id.fragmenKayitOl_spEMail);

        btnKayitOl = (Button) findViewById(R.id.kayitEkrani_btnKayitOl);
        //endregion

        //region setText
        String m = getIntent().getStringExtra("mail");
        if (m != null && m.contains("@")) {
            switch (m.split("@")[1]) {
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
            m = m.replace(m.split("@")[1], "");
            m = m.replace("@", "");
        }
        edtTxtEMail.setText(m);
        edtTxtSifre.setText(getIntent().getStringExtra("sifre"));
        //endregion

        //region kayıt ol onClick
        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(bk.adSoyadKontrol(edtTxtAdSoyad, tilAdSoyad) ||
                        bk.telNoKontrol(edtTxtTelNo, tilTelNo) ||
                        bk.mailKontrol(edtTxtEMail, tilEMail, spEMail) ||
                        bk.sifreKontrol(edtTxtSifre, tilSifre) ||
                        bk.sifreTekrarKontrol(edtTxtSifre, edtTxtSifreTekrar, tilSifreTekrar))) {
                    //hiç hata yoksa
                    _Kullanici k = new _Kullanici(edtTxtAdSoyad.getText().toString().trim(), edtTxtTelNo.getText().toString().trim(), edtTxtEMail.getText().toString().trim() + "" + spEMail.getSelectedItem().toString(), edtTxtSifre.getText().toString().trim(), 1);
                    vk.kullaniciOlustur(k);
                    Intent intent = new Intent(KayitEkrani.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(KayitEkrani.this, "Lütfen tüm bilgileri hatasız giriniz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //endregion

    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }
}