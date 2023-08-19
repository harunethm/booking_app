package com.example.booking_app;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class BilgiKontrol {
    _veriKaynagi vk;

    public BilgiKontrol(Context c) {
        vk = new _veriKaynagi(c);
    }

    boolean adSoyadKontrol(EditText editText, TextInputLayout textInputLayout) { // hata var ise true yok ise false döndürecek
        textInputLayout.setError("");
        String adSoyad = editText.getText().toString().trim();
        int isimler = adSoyad.split(" ").length;
        if (isimler < 2) { // bir ad ve bir soyad olmak üzere en az iki kelime olmalı
            textInputLayout.setError("Lütfen adınızı ve soyadınızı tam giriniz.");
            return true;
        }
        return false;
    }

    boolean sifreKontrol(EditText editText, TextInputLayout textInputLayout) { // hata var ise true yok ise false döndürecek
        textInputLayout.setError("");

        String sifre = editText.getText().toString().trim();
        String[] sifreKarakterleri = sifre.split("");
        String harfler = "qwertyuıopğüasdfghjklşizxcvbnmöç", sayilar = "0123456789";
        boolean harf = false, sayi = false;

        if (sifre.contains(" ")) { //şifrede boşluk olmamalı
            textInputLayout.setError("Şifre boşluk içermemelidir.");
            return true;
        } else if (sifre.length() < 8) { //şifre en az 8 karakter olmalı
            textInputLayout.setError("Şifre en az 8 karakterden oluşmalıdır.");
            return true;
        } else { // şifre en az 1 harf ve en az 1 sayı içermeli
            for (String s : sifreKarakterleri) {
                if (!harf || !sayi) {
                    if (harfler.contains(s))
                        harf = true; // harf içeriyorsa true oluyor
                    if (sayilar.contains(s))
                        sayi = true; // sayı içeriyorsa true oluyor
                } else
                    break; // ikisi de bulunduysa artık döngü durabilir
            }
            if (!sayi) { // şifre en az 1 sayı içermeli
                textInputLayout.setError("Şifre en az 1 rakam içermelidir.");
                return true;
            } else if (!harf) { // şifre en az 1 harf içermeli
                textInputLayout.setError("Şifre en az 1 harf içermelidir.");
                return true;
            }
        }
        return false;
    }

    boolean sifreTekrarKontrol(EditText editTextSifre, EditText editTextTekrar, TextInputLayout textInputLayout) { // hata var ise true yok ise false döndürecek
        textInputLayout.setError("");

        String sifre = editTextSifre.getText().toString().trim();
        String sifreTekrar = editTextTekrar.getText().toString().trim();

        if (!sifre.equals(sifreTekrar)) {
            textInputLayout.setError("Şifreler Uyuşmuyor.");
            return true;
        } else {
            textInputLayout.setError("");
            return false;
        }
    }

    boolean mailKontrol(EditText editText, TextInputLayout textInputLayout, Spinner sp) { // hata var ise true yok ise false döndürecek
        textInputLayout.setError("");

        String mail = editText.getText().toString().trim();

        if (mail.contains("@") || mail.contains(".com") || mail.equals("")) {
            textInputLayout.setError("Lütfen geçerli bir mail adresi giriniz.");
            return true;
        }

        mail += sp.getSelectedItem().toString();
        int ID = vk.kullaniciID("eMail = ?", new String[]{mail});
        if (ID > 0) {
            textInputLayout.setError("Bu mail adresi zaten kayıtlı.");
            return true;
        }
        return false;
    }

    boolean telNoKontrol(EditText editText, TextInputLayout textInputLayout) {
        String telNo = editText.getText().toString().trim();
        int ID = vk.kullaniciID("telNo = ?", new String[]{telNo});
        if (ID > 0) {
            textInputLayout.setError("Telefon numarası zaten kayıtlı.");
            return true;
        } else if (telNo.length() < 10) {
            textInputLayout.setError("Lütfen geçerli bir telefon numarası giriniz.");
            return true;
        } else {
            textInputLayout.setError("");
            return false;
        }
    }

    boolean mailKontrol(EditText editText, TextInputLayout textInputLayout, Spinner sp, int id) {
        textInputLayout.setError("");
        String mail = editText.getText().toString().trim();
        if (mail.contains("@") || mail.contains(".com") || mail.equals("")) {
            textInputLayout.setError("Lütfen geçerli bir mail adresi giriniz.");
            return true;
        }
        mail += sp.getSelectedItem().toString();
        int ID = vk.kullaniciID("eMail = ? and ID != ?", new String[]{mail, "" + id});
        if (ID > 0) {
            textInputLayout.setError("Bu mail adresi zaten kayıtlı.");
            return true;
        }
        return false;
    }

    boolean telNoKontrol(EditText editText, TextInputLayout textInputLayout, int id) {
        textInputLayout.setError("");
        String telNo = editText.getText().toString().trim();
        int ID = vk.kullaniciID("telNo = ? and ID != ?", new String[]{telNo, "" + id});
        if (ID > 0) {
            textInputLayout.setError("Telefon numarası zaten kayıtlı.");
            return true;
        } else if (telNo.length() < 10) {
            textInputLayout.setError("Lütfen geçerli bir telefon numarası giriniz.");
            return true;
        }
        return false;
    }


}
