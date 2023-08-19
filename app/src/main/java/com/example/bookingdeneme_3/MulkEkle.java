package com.example.bookingdeneme_3;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Images.Media.getBitmap;

public class MulkEkle extends Fragment {

    //region tanımlamalar
    ListView resimler;
    Button btnResimEkle;
    TabLayout tabLayout;
    EditText edtTxtBaslik, edtTxtUcret;
    FloatingActionButton fabKaydet;
    ViewPager viewPager;

    Context context;
    List<Bitmap> bitmapList;
    _veriKaynagi vk;
    View view;
    _Mulk mulk = null;

    String baslik, aciklama, gunlukAylik, mulkTipi, odaSayisi, katSayisi, isitma, cephe, katNo;
    int esyali, yalitim, asansor;
    float ucret;
    String adres;

    static final int resimSec = 1;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    //eğer false ise ekleme modu, eğer true ise düzenleme modu
    boolean ekle_duzenle = false;

    //endregion

    //region constructor
    public MulkEkle(_Mulk mulk) {
        this.mulk = mulk;
        this.ekle_duzenle = true;
    }

    public MulkEkle() {
        this.ekle_duzenle = false;
    }

    //endregion
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // region tanımlamalar
        view = inflater.inflate(R.layout.fragment_mulk_ekle, container, false);

        //region tablayout ayarlaması
        viewPager = (ViewPager) view.findViewById(R.id.fragmentMulkEkle_fragment);
        tabLayout = (TabLayout) view.findViewById(R.id.fragmentMulkEkle_tabLayout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        //endregion

        resimler = view.findViewById(R.id.fragmentMulkEkle_resimler);
        btnResimEkle = (Button) view.findViewById(R.id.fragmentMulkEkle_btnResimEkle);
        fabKaydet = (FloatingActionButton) view.findViewById(R.id.fragmentMulkEkle_fabKaydet);

        edtTxtBaslik = (EditText) view.findViewById(R.id.fragmentMulkEkle_edtTxtBaslik);
        edtTxtUcret = (EditText) view.findViewById(R.id.fragmentMulkEkle_edtTxtUcret);

        context = this.getActivity();
        bitmapList = new ArrayList<>();
        vk = new _veriKaynagi(context);

        sharedPref = context.getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        // endregion

        //region ekle_duzenle
        if (ekle_duzenle && mulk != null) { //düzenleme modunda açılmış ve mülk başarıyla eklenmiş ise
            edtTxtBaslik.setText(mulk.getBaslik());
            bitmapList = mulk.getResimler();
            resimler.setAdapter(new ArrAdpResimler(context, bitmapList));
            edtTxtUcret.setText(mulk.getUcret().toString());
        }
        //endregion

        // region resim ekle onClick
        btnResimEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Resim Seç"), resimSec);
            }
        });
        // endregion

        //region kaydet onClick
        fabKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hataKontrol()) {

                    baslik = edtTxtBaslik.getText().toString().trim();
                    ucret = edtTxtUcret.getText().toString().trim().equals("") ? 0 : Float.parseFloat(edtTxtUcret.getText().toString().trim());

                    aciklama = sharedPref.getString("aciklama", "Mülk sahibi herhangi bir açıklama girmedi.");
                    adres = sharedPref.getString("adres", "");
                    gunlukAylik = sharedPref.getString("gunlukAylik", "");
                    mulkTipi = sharedPref.getString("mulkTipi", "");
                    odaSayisi = sharedPref.getString("odaSayisi", "");
                    katSayisi = sharedPref.getString("katSayisi", "");
                    isitma = sharedPref.getString("isitma", "");
                    cephe = sharedPref.getString("cephe", "");
                    katNo = sharedPref.getString("katNo", "");
                    esyali = sharedPref.getInt("esyali", 0);
                    yalitim = sharedPref.getInt("yalitim", 0);
                    asansor = sharedPref.getInt("asansor", 0);

                    editor.putString("aciklama", "");
                    editor.putString("adres", "");
                    editor.putString("gunlukAylik", "");
                    editor.putString("mulkTipi", "");
                    editor.putString("odaSayisi", "");
                    editor.putString("katSayisi", "");
                    editor.putString("isitma", "");
                    editor.putString("cephe", "");
                    editor.putString("katNo", "");
                    editor.putInt("esyali", 2);
                    editor.putInt("yalitim", 2);
                    editor.putInt("asansor", 2);
                    editor.apply();

                    _Mulk mulkEkle = new _Mulk(_veriKaynagi._id, baslik, aciklama, adres, mulkTipi, gunlukAylik, ucret, katNo, katSayisi, odaSayisi, isitma, cephe, asansor, yalitim, esyali, bitmapList);

                    if (ekle_duzenle) {
                        mulkEkle.setID(mulk.getID());
                        vk.mulkGuncelle(mulkEkle);
                    } else {
                        vk.mulkEkle(mulkEkle);
                    }
                    getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, new MevcutMulkler()).commit();
                }
            }
        });
        //endregion

        //region resimlerden resim silme
        resimler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vk.mulkResimSil((int) id, bitmapList.get(position));
                bitmapList.remove(position);
                resimler.setAdapter(new ArrAdpResimler(context, bitmapList, (int) id));
            }
        });

        //endregion

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == resimSec && resultCode == RESULT_OK && data != null) {
                if (data.getClipData() == null) {
                    Bitmap bitmap = getBitmap(context.getContentResolver(), data.getData());
                    bitmapList.add(bitmap);
                } else {
                    int count = 0;
                    ClipData clipData = data.getClipData();
                    count = clipData.getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri uri = clipData.getItemAt(i).getUri();
                        Bitmap bitmap = getBitmap(context.getContentResolver(), uri);
                        bitmapList.add(bitmap);
                    }
                }
                resimler.setAdapter(new ArrAdpResimler(context, bitmapList));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hataKontrol() {
        if (edtTxtBaslik.getText().toString().trim().equals("")) {
            Toast.makeText(context, "Lütfen bir başlık girin.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (edtTxtUcret.getText().toString().trim().equals("") || Float.parseFloat(edtTxtUcret.getText().toString()) < 0) {
            Toast.makeText(context, "Lütfen geçerli bir ücret bilgisi girin.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (bitmapList.size() < 1) {
            Toast.makeText(context, "Lütfen en az 1 resim ekleyin.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (sharedPref.getBoolean("ozellikHata", true)) {
            Toast.makeText(context, "Tüm bilgileri doğru doldurduğunuzdan emin olun.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    void setupViewPager(ViewPager viewPager) {
        AdaptorPage adaptor = new AdaptorPage(getChildFragmentManager(), 1);
        if (ekle_duzenle) {
            adaptor.addFragment(new MulkEkleOzellikler(mulk), "Özellikler");
            adaptor.addFragment(new MulkEkleAciklama(mulk), "Açıklama");
            adaptor.addFragment(new MulkEkleAdres(mulk), "Adres");
        } else {
            adaptor.addFragment(new MulkEkleOzellikler(), "Özellikler");
            adaptor.addFragment(new MulkEkleAciklama(), "Açıklama");
            adaptor.addFragment(new MulkEkleAdres(), "Adres");
        }
        viewPager.setAdapter(adaptor);
    }
}