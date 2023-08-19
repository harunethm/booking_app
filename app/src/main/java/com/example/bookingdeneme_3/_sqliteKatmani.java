package com.example.bookingdeneme_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class _sqliteKatmani extends SQLiteOpenHelper {

    private static final String db_adi = "bookingApp";
    private static int db_versiyon = 13;

    public _sqliteKatmani(Context context) {
        super(context, db_adi, null, db_versiyon);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String kullaniciTablosu = "create table kullanici (ID integer primary key, profilResmi blob, adSoyad text, telNo text, eMail text, sifre text, aktifMi integer)";
        String mulkTablosu = "create table mulk (ID integer primary key, kullaniciID integer, baslik text, aciklama text, adres text, mulkTipi text, gunlukAylik text, katNo text, katSayisi text, odaSayisi text, isitma text, cephe text, ucret real, asansor integer, yalitim integer, esyali integer, kapakResmi blob)";
        String mulkResimleriTablosu = "create table mulkResimleri (ID integer primary key, mulkID integer, resim blob)";
        db.execSQL(kullaniciTablosu);
        db.execSQL(mulkTablosu);
        db.execSQL(mulkResimleriTablosu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table kullanici");
        db.execSQL("drop table mulk");
        db.execSQL("drop table mulkResimleri");
        onCreate(db);
    }

    public static int getDb_versiyon() {
        return db_versiyon;
    }

    public static void setDb_versiyon(int db_versiyon) {
        _sqliteKatmani.db_versiyon = db_versiyon;
    }
}
