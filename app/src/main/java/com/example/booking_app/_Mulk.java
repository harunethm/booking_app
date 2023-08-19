package com.example.booking_app;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class _Mulk implements Serializable {

    //region mülk bilgileri
    int ID;
    int kullaniciID;

    String baslik;
    String aciklama;
    String adres;
    String mulkTipi;
    String gunlukAylik;
    String katSayisi;
    String odaSayisi;
    String isitma;
    String cephe;
    Float  ucret;

    String katNo;
    int asansor;
    int yalitim;
    int esyali;

    List<Bitmap> resimler;
    //endregion

    //region constructor
    public _Mulk(int kullaniciID, String baslik, String aciklama, String adres, String mulkTipi, String gunlukAylik, Float ucret, String katNo, String katSayisi, String odaSayisi, String isitma, String cephe, int asansor, int yalitim, int esyali, List<Bitmap> resimler) {
        this.kullaniciID = kullaniciID;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.adres = adres;
        this.mulkTipi = mulkTipi;
        this.gunlukAylik = gunlukAylik;
        this.ucret = ucret;
        this.katNo = katNo;
        this.katSayisi = katSayisi;
        this.odaSayisi = odaSayisi;
        this.isitma = isitma;
        this.cephe = cephe;
        this.asansor = asansor;
        this.yalitim = yalitim;
        this.esyali = esyali;
        this.resimler = resimler;
    }
    //endregion

    //region getters and setters

    public int getID() {
        return ID;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }

    public String getBaslik() {
        return baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public String getAdres() {
        return adres;
    }

    public String getMulkTipi() {
        return mulkTipi;
    }

    public String getGunlukAylik() {
        return gunlukAylik;
    }

    public String getKatSayisi() {
        return katSayisi;
    }

    public String getOdaSayisi() {
        return odaSayisi;
    }

    public String getIsitma() {
        return isitma;
    }

    public String getCephe() {
        return cephe;
    }

    public Float getUcret() {
        return ucret;
    }

    public String getKatNo() {
        return katNo;
    }

    public int getAsansor() {
        return asansor;
    }

    public int getYalitim() {
        return yalitim;
    }

    public int getEsyali() {
        return esyali;
    }

    public List<Bitmap> getResimler() {
        return resimler;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setCephe(String cephe) {
        this.cephe = cephe;
    }

    public void setAsansor(int asansor) {
        this.asansor = asansor;
    }

    public void setYalitim(int yalitim) {
        this.yalitim = yalitim;
    }


    //endregion
}
