package com.example.booking_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class Mulk extends Fragment {

    //region tanımlamalar
    TextView baslik;
    LinearLayout resimler;
    TabLayout tabLayout;

    List<Bitmap> bitmapList;
    View view;

    ViewPager viewPager;

    Context context;

    _Mulk mulk;
    //endregion

    //region constructor
    public Mulk(_Mulk mulk) {
        this.mulk = mulk;
    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //region tanımlamalar
        view = inflater.inflate(R.layout.fragment_mulk, container, false);
        context = this.getActivity();
        //region tablayout ayarlaması
        viewPager = (ViewPager) view.findViewById(R.id.fragmentMulk_fragment);
        tabLayout = (TabLayout) view.findViewById(R.id.fragmentMulk_tabLayout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        //endregion

        baslik = view.findViewById(R.id.fragmentMulk_tvBaslik);
        resimler = view.findViewById(R.id.fragmentMulk_resimler);

        baslik.setText(mulk.getBaslik());

        //region resimleri ekleme
        bitmapList = mulk.getResimler();
        lvEkle(bitmapList);
        //endregion

        //endregion
        return view;
    }

    private void lvEkle(List<Bitmap> bitmaps) {
        if (bitmaps != null) {
            for (Bitmap bitmap : bitmaps){
                LayoutInflater inflater = LayoutInflater.from(context);
                View v = inflater.inflate(R.layout.mulk_resimleri, resimler, false);
                ImageView imageView = v.findViewById(R.id.mulkResimleri_imageView);
                imageView.setImageBitmap(bitmap);
                resimler.addView(v);
            }
        }
    }

    void setupViewPager(ViewPager viewPager) {
        AdaptorPage adaptor = new AdaptorPage(getChildFragmentManager(), 1);
        adaptor.addFragment(new MulkOzellikler(mulk), "Özellikler");
        adaptor.addFragment(new MulkAciklama(mulk.getAciklama()), "Açıklama");
        adaptor.addFragment(new MulkAdres(mulk.getAdres()), "Adres");
        viewPager.setAdapter(adaptor);
    }
}