package com.example.bookingdeneme_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _veriKaynagi {

    //region db ile ilgili kısımlar
    SQLiteDatabase db;
    _sqliteKatmani bdb;
    static int _id = 0;

    public _veriKaynagi(Context c) {
        bdb = new _sqliteKatmani(c);
    }

    public void versiyonGuncelle() {
        _sqliteKatmani.setDb_versiyon(_sqliteKatmani.getDb_versiyon() + 1);
    }

    private void baglanti(int i) {
        switch (i) {
            case 0:
                db = bdb.getReadableDatabase();
                break;
            case 1:
                db = bdb.getWritableDatabase();
                break;
            case 2:
                bdb.close();
                break;
            default:
                break;
        }
    }
    //endregion

    //region kullanıcı ile ilgili kısımlar
    public void kullaniciOlustur(_Kullanici kullanici) {
        try {
            baglanti(1);
            ContentValues cv = new ContentValues();
            if (kullanici.getProfilResmi() != null)
                cv.put("profilResmi", bitmapToByteArr(kullanici.getProfilResmi()));
            cv.put("adSoyad", kullanici.getAdSoyad());
            cv.put("telNo", kullanici.getTelNo());
            cv.put("eMail", kullanici.geteMail());
            cv.put("sifre", kullanici.getSifre());
            cv.put("aktifMi", kullanici.getAktifMi());
            _id = (int) db.insert("kullanici", null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }

    public List<_Kullanici> kullanicilariListele() {
        List<_Kullanici> kullaniciList = new ArrayList<_Kullanici>();
        try {
            baglanti(0);

            String[] kolonlar = {"ID", "adSoyad", "telNo", "eMail", "sifre", "aktifMi"};

            Cursor cursor = db.query("kullanici", kolonlar, null, null, null, null, "ID");
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("ID"));

                String adSoyad = cursor.getString(cursor.getColumnIndex("adSoyad"));
                String telNo = cursor.getString(cursor.getColumnIndex("telNo"));
                String eMail = cursor.getString(cursor.getColumnIndex("eMail"));
                String sifre = cursor.getString(cursor.getColumnIndex("sifre"));

                int aktifMi = cursor.getInt(cursor.getColumnIndex("aktifMi"));

                _Kullanici k = new _Kullanici(adSoyad, telNo, eMail, sifre, aktifMi);
                k.setID(ID);

                kullaniciList.add(k);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
        return kullaniciList;
    }

    public _Kullanici kullaniciSec() {
        _Kullanici k = null;
        try {
            baglanti(0);

            String[] kolonlar = {"ID", "profilResmi", "adSoyad", "telNo", "eMail", "sifre", "aktifMi"};
            Cursor cursor = db.query("kullanici", kolonlar, "ID = ?", new String[]{"" + _id}, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                String adSoyad = cursor.getString(cursor.getColumnIndex("adSoyad"));
                String telNo = cursor.getString(cursor.getColumnIndex("telNo"));
                String eMail = cursor.getString(cursor.getColumnIndex("eMail"));
                String sifre = cursor.getString(cursor.getColumnIndex("sifre"));
                int aktifMi = cursor.getInt(cursor.getColumnIndex("aktifMi"));

                byte[] byteArr = cursor.getBlob(cursor.getColumnIndex("profilResmi"));
                if (byteArr != null) {
                    Bitmap profilResmi = byteArrToBitmap(byteArr);
                    k = new _Kullanici(profilResmi, adSoyad, telNo, eMail, sifre, aktifMi);
                } else
                    k = new _Kullanici(adSoyad, telNo, eMail, sifre, aktifMi);

                k.setID(_id);
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
        return k;
    }

    public int kullaniciID(String kosullar, String[] degerler) {
        try {
            baglanti(0);
            Cursor cursor = db.query("kullanici", new String[]{"ID"}, kosullar, degerler, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                _id = cursor.getInt(cursor.getColumnIndex("ID"));
                cursor.close();
                return _id;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            baglanti(2);
        }
    }

    public void kisiGuncelle(_Kullanici k) {
        try {
            baglanti(1);
            ContentValues cv = new ContentValues();
            if (k.getProfilResmi() != null)
                cv.put("profilResmi", bitmapToByteArr(k.getProfilResmi()));
            cv.put("adSoyad", k.getAdSoyad());
            cv.put("telNo", k.getTelNo());
            cv.put("eMail", k.geteMail());
            cv.put("sifre", k.getSifre());
            db.update("kullanici", cv, "ID=?", new String[]{"" + k.getID()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }

    public void aktiflikGuncelle(int aktiflik) {
        try {
            baglanti(1);
            ContentValues cv = new ContentValues();
            cv.put("aktifMi", aktiflik);
            db.update("kullanici", cv, "ID = ?", new String[]{"" + _id});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }
    //endregion

    //region mülk ile ilgili kısımlar
    public void mulkEkle(_Mulk mulk) {
        try {
            baglanti(1);

            ContentValues cv = new ContentValues();
            cv.put("kullaniciID", mulk.getKullaniciID());
            cv.put("baslik", mulk.getBaslik());
            cv.put("aciklama", mulk.getAciklama());
            cv.put("adres", mulk.getAdres());
            cv.put("mulkTipi", mulk.getMulkTipi());
            cv.put("gunlukAylik", mulk.getGunlukAylik());
            cv.put("katNo", mulk.getKatNo());
            cv.put("katSayisi", mulk.getKatSayisi());
            cv.put("odaSayisi", mulk.getOdaSayisi());
            cv.put("isitma", mulk.getIsitma());
            cv.put("cephe", mulk.getCephe());
            cv.put("ucret", mulk.getUcret());
            cv.put("asansor", mulk.getAsansor());
            cv.put("yalitim", mulk.getYalitim());
            cv.put("esyali", mulk.getEsyali());

            int mulkID = (int) db.insert("mulk", null, cv);
            mulkResimEkle(mulkID, mulk.getResimler());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }

    public void mulkSil(int ID) {
        try {
            baglanti(1);
            db.delete("mulkResimleri", "mulkID = ?", new String[]{ID + ""});
            db.delete("mulk", "ID= ?", new String[]{ID + ""});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }

    public _Mulk mulkSec(int mulkID) {
        _Mulk mulk = null;
        try {
            baglanti(0);

            String[] kolonlar = {"kullaniciID", "baslik", "aciklama", "adres", "mulkTipi", "gunlukAylik", "katNo", "katSayisi", "odaSayisi", "isitma", "cephe", "ucret", "asansor", "yalitim", "esyali"};

            Cursor cursor = db.query("mulk", kolonlar, "ID=?", new String[]{mulkID + ""}, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                int kullaniciID = cursor.getInt(cursor.getColumnIndex("kullaniciID"));
                int asansor = cursor.getInt(cursor.getColumnIndex("asansor"));
                int yalitim = cursor.getInt(cursor.getColumnIndex("yalitim"));
                int esyali = cursor.getInt(cursor.getColumnIndex("esyali"));
                float ucret = cursor.getFloat(cursor.getColumnIndex("ucret"));
                String katNo = cursor.getString(cursor.getColumnIndex("katNo"));
                String baslik = cursor.getString(cursor.getColumnIndex("baslik"));
                String aciklama = cursor.getString(cursor.getColumnIndex("aciklama"));
                String adres = cursor.getString(cursor.getColumnIndex("adres"));
                String mulkTipi = cursor.getString(cursor.getColumnIndex("mulkTipi"));
                String gunlukAylik = cursor.getString(cursor.getColumnIndex("gunlukAylik"));
                String katSayisi = cursor.getString(cursor.getColumnIndex("katSayisi"));
                String odaSayisi = cursor.getString(cursor.getColumnIndex("odaSayisi"));
                String isitma = cursor.getString(cursor.getColumnIndex("isitma"));
                String cephe = cursor.getString(cursor.getColumnIndex("cephe"));

                List<Bitmap> resimler = mulkResimGetir(mulkID);

                mulk = new _Mulk(kullaniciID, baslik, aciklama, adres, mulkTipi, gunlukAylik, ucret, katNo, katSayisi, odaSayisi, isitma, cephe, asansor, yalitim, esyali, resimler);
                mulk.setID(mulkID);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
        return mulk;
    }

    public List<_Mulk> mulkleriListele(String selection, String[] selectionArgs, String adres) {
        List<_Mulk> mulkler = new ArrayList<>();
        try {
            baglanti(0);
            String[] kolonlar = new String[]{"ID"};
            Cursor cursor = db.query("mulk", kolonlar, selection, selectionArgs, null, null, "adres");
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int ID = cursor.getInt(cursor.getColumnIndex("ID"));
                    if (mulkSec(ID).getAdres().toLowerCase().contains(adres.toLowerCase()))
                        mulkler.add(mulkSec(ID));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
        return mulkler;
    }

    public void mulkGuncelle(_Mulk mulk) {
        try {
            baglanti(1);

            ContentValues cv = new ContentValues();
            cv.put("kullaniciID", mulk.kullaniciID);
            cv.put("baslik", mulk.getBaslik());
            cv.put("aciklama", mulk.getAciklama());
            cv.put("adres", mulk.getAdres());
            cv.put("mulkTipi", mulk.getMulkTipi());
            cv.put("gunlukAylik", mulk.getGunlukAylik());
            cv.put("katSayisi", mulk.getKatSayisi());
            cv.put("odaSayisi", mulk.getOdaSayisi());
            cv.put("isitma", mulk.getIsitma());
            cv.put("cephe", mulk.getCephe());
            cv.put("ucret", mulk.getUcret());
            cv.put("katNo", mulk.getKatNo());
            cv.put("asansor", mulk.getAsansor());
            cv.put("yalitim", mulk.getYalitim());
            cv.put("esyali", mulk.getEsyali());

            db.update("mulk", cv, "ID = ?", new String[]{mulk.getID() + ""});
            mulkResimSil(mulk.getID());
            mulkResimEkle(mulk.getID(), mulk.getResimler());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }
    //endregion

    //region resimler ile ilgili kısımlar
    public void mulkResimEkle(int mulkID, List<Bitmap> bitmapList) {
        try {
            baglanti(1);
            for (Bitmap bitmap : bitmapList) {
                ContentValues cv = new ContentValues();
                cv.put("mulkID", mulkID);
                cv.put("resim", bitmapToByteArr(bitmap));
                db.insert("mulkResimleri", null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }

    public void mulkResimSil(int mulkID, Bitmap bitmap) {
        try {
            baglanti(1);
            byte[] resim = bitmapToByteArr(bitmap);
            Cursor cursor = db.query("mulkResimleri", new String[]{"ID", "resim"}, "mulkID = ?", new String[]{"" + mulkID}, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int ID = cursor.getInt(cursor.getColumnIndex("ID"));
                    byte[] res = cursor.getBlob(cursor.getColumnIndex("resim"));
                    if (Arrays.equals(res, resim)) {
                        db.delete("mulkResimleri", "ID = ?", new String[]{ID + ""});
                        break;
                    }
                    cursor.moveToNext();
                }
            }
            cursor.close();
            baglanti(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mulkResimSil(int mulkID) {
        try {
            baglanti(1);

            db.delete("mulkResimleri", "mulkID = ?", new String[]{mulkID + ""});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baglanti(2);
        }
    }

    public List<Bitmap> mulkResimGetir(int mulkID) {
        try {
            List<Bitmap> resimler = new ArrayList<Bitmap>();

            Cursor c = db.query("mulkResimleri", new String[]{"resim"}, "mulkID=?", new String[]{"" + mulkID}, null, null, null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    byte[] byteArr = c.getBlob(c.getColumnIndex("resim"));
                    resimler.add(byteArrToBitmap(byteArr));
                    c.moveToNext();
                }
                c.close();
            }
            return resimler;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int mulkResimIDSec(int mulkID, Bitmap bitmap) {
        try {
            baglanti(0);
            byte[] resim = bitmapToByteArr(bitmap);
            Cursor cursor = db.query("mulkResimleri", new String[]{"ID", "resim"}, "mulkID = ?", new String[]{mulkID + ""}, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    byte[] res = cursor.getBlob(cursor.getColumnIndex("resim"));
                    if (Arrays.equals(resim, res)) {
                        return cursor.getInt(cursor.getColumnIndex("ID"));
                    }
                    cursor.moveToNext();
                }
            }
            cursor.close();
            baglanti(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //endregion

    //region bitmap - byte dönüşümleri
    public static byte[] bitmapToByteArr(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public static Bitmap byteArrToBitmap(byte[] byteArr) {
        return BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
    }
    //endregion
}