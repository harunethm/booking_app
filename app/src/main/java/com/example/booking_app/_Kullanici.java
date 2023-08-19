package com.example.booking_app;

import android.graphics.Bitmap;

import java.io.Serializable;

public class _Kullanici implements Serializable {

    //region kullanıcı bilgileri
    int ID = 0;
    Bitmap profilResmi = null;

    String adSoyad;
    String telNo;
    String eMail;
    String sifre;

    int aktifMi;
    //endregion

    //region constructor
    public _Kullanici(Bitmap profilResmi, String adSoyad, String telNo, String eMail, String sifre, int aktifMi) {
        this.profilResmi = profilResmi;
        this.adSoyad = adSoyad;
        this.telNo = telNo;
        this.eMail = eMail;
        this.sifre = sifre;
        this.aktifMi = aktifMi;
    }

    public _Kullanici(String adSoyad, String telNo, String eMail, String sifre, int aktifMi) {
        this.adSoyad = adSoyad;
        this.telNo = telNo;
        this.eMail = eMail;
        this.sifre = sifre;
        this.aktifMi = aktifMi;
    }
    //endregion

    //region getter and setters
    public Bitmap getProfilResmi() {
        return profilResmi;
    }

    public void setProfilResmi(Bitmap profilResmi) {
        this.profilResmi = profilResmi;
    }

    public int getAktifMi() {
        return aktifMi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    //endregion
}
