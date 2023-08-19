package com.example.booking_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MevcutMulkler extends Fragment {

    //region tanımlamalar
    FloatingActionButton fabMenu, fabMulkEkle, fabFiltrele;
    TextView txtFiltrele, txtMulkEkle;
    Animation acilisAnimasyonu, kapanisAnimasyonu;
    SwipeMenuListView lv;

    List<_Mulk> mulkler = new ArrayList<_Mulk>();
    Context context;
    Fragment f;
    _veriKaynagi vk;

    boolean acikMi;
    boolean filtreleme = false;
    List<Spinner> spinnerList = new ArrayList<>();
    String min = "", max = "", adres = "";
    //endregion

    //region constructor
    public MevcutMulkler() {
        filtreleme = false;
    }

    public MevcutMulkler(List<Spinner> spinnerList, String min, String max ,String adres) {
        filtreleme = true;
        this.min = min;
        this.max = max;
        this.adres = adres;
        this.spinnerList = spinnerList;
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //region tanımlamalar
        final View view = inflater.inflate(R.layout.fragment_mevcut_mulkler, container, false);
        context = this.getActivity();

        acilisAnimasyonu = AnimationUtils.loadAnimation(context, R.anim.fab_in);
        kapanisAnimasyonu = AnimationUtils.loadAnimation(context, R.anim.fab_out);

        txtMulkEkle = view.findViewById(R.id.fragmentMevcutMulkler_txtMulkEkle);
        txtFiltrele = view.findViewById(R.id.fragmentMevcutMulkler_txtFiltrele);

        lv = view.findViewById(R.id.fragmentMevcutMulkler_listView);

        fabMenu = view.findViewById(R.id.fragmentMevcutMulkler_fabMenu);
        fabMulkEkle = view.findViewById(R.id.fragmentMevcutMulkler_fabMulkEkle);
        fabFiltrele = view.findViewById(R.id.fragmentMevcutMulkler_fabFiltrele);

        vk = new _veriKaynagi(context);
        acikMi = false;
        //endregion

        //region mülkleri listView'a ekleme
        mulkGoster();
        //endregion

        //region floating action button mülk ekle click
        fabMulkEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = new MulkEkle();
                getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
            }
        });
        //endregion

        //region floating action button filtrele click
        fabFiltrele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = new MulkFiltrele();
                getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
            }
        });
        //endregion

        //region floating action button menü click
        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acikMi) { //menu acik ise kapat
                    fabMulkEkle.startAnimation(kapanisAnimasyonu);
                    fabFiltrele.startAnimation(kapanisAnimasyonu);
                    txtMulkEkle.startAnimation(kapanisAnimasyonu);
                    txtFiltrele.startAnimation(kapanisAnimasyonu);
                    fabMenu.setImageResource(R.drawable.menu_black);
                    acikMi = false;
                } else { // menu kapalı ise aç
                    fabMulkEkle.startAnimation(acilisAnimasyonu);
                    fabFiltrele.startAnimation(acilisAnimasyonu);
                    txtMulkEkle.startAnimation(acilisAnimasyonu);
                    txtFiltrele.startAnimation(acilisAnimasyonu);
                    fabMenu.setImageResource(R.drawable.cross);
                    acikMi = true;
                }
            }
        });
        //endregion

        //region listView click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                f = new Mulk(vk.mulkSec((int) id));
                getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
            }
        });
        //endregion

        //region swipe menü oluştur
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem duzenle = new SwipeMenuItem(context);
                duzenle.setBackground(R.color.duzenleBackGround);
                duzenle.setWidth(256);
                duzenle.setTitle("Düzenle");
                duzenle.setTitleColor(R.color.colorText);
                duzenle.setIcon(context.getResources().getDrawable(R.drawable.edit, context.getTheme()));
                menu.addMenuItem(duzenle);

                SwipeMenuItem sil = new SwipeMenuItem(context);
                sil.setBackground(R.color.silBackGround);
                sil.setWidth(256);
                sil.setTitle("Sil");
                sil.setTitleColor(R.color.colorText);
                sil.setIcon(context.getResources().getDrawable(R.drawable.delete, context.getTheme()));
                menu.addMenuItem(sil);
            }
        };
        lv.setMenuCreator(creator);
        //endregion

        //region swipe menü click
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // düzenle
                        f = new MulkEkle(mulkler.get(position));
                        getParentFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
                        break;
                    case 1:
                        // sil
                        vk.mulkSil(mulkler.get(position).getID());
                        mulkGoster();
                        break;
                    default:
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        //endregion
        return view;
    }

    private void mulkGoster() {
        String selection = "kullaniciID=?";
        String selectionArgs = _veriKaynagi._id + "";

        if (!min.equals("")) {
            selection += " and ucret>?";
            selectionArgs += "_" + min;


        }
        if (!max.equals("")) {
            selection += " and ucret<? ";
            selectionArgs += "_" + min;

        }

        for (int i = 0; i < spinnerList.size(); i++) {
            if (!spinnerList.get(i).getSelectedItem().toString().contains("Seçiniz")) {
                selection += " and ";
                switch (i) {
                    case 0:
                        selection += "gunlukAylik=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 1:
                        selection += "mulkTipi=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 2:
                        selection += "odaSayisi=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 3:
                        selection += "katNo=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 4:
                        selection += " atSayisi=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 5:
                        selection += "esyali=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 6:
                        selection += "isitma=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 7:
                        selection += "cephe=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 8:
                        selection += "yalitim=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    case 9:
                        selection += "asansor=?";
                        selectionArgs += "_" + spinnerList.get(i).getSelectedItem().toString();

                        break;
                    default:
                        break;
                }
            }
        }

        String[] args = selectionArgs.split("_");

        mulkler = vk.mulkleriListele(selection, args, adres);
        if (mulkler != null)
            lv.setAdapter(new ArrAdpMevcutMulkler(context, mulkler));
    }
}
