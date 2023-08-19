package com.example.booking_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArrAdpMevcutMulkler extends BaseAdapter {

    List<_Mulk> mulkler;
    LayoutInflater inflater;
    Context context;

    public ArrAdpMevcutMulkler(Context context, List<_Mulk> mulk) {
        this.context = context;
        this.mulkler = mulk;
    }

    @Override
    public int getCount() {
        //listView de gösterilecek satır sayısını verir
        return mulkler.size();
    }

    @Override
    public Object getItem(int position) {
        //position ile sırası gelen eleman
        return position;
    }

    @Override
    public long getItemId(int position) {
        //eğer var ise niteleyici id bilgisi
        return mulkler.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(context);
        View custom_listView = inflater.inflate(R.layout.custom_listview_mulk, null);

        ImageView resim = custom_listView.findViewById(R.id.cl_mulk_resim);
        TextView txtVwBaslik = custom_listView.findViewById(R.id.cl_mulk_baslik);
        TextView txtVwAdres = custom_listView.findViewById(R.id.cl_mulk_adres);

        String baslik = mulkler.get(position).getBaslik();
        String adres = mulkler.get(position).getAdres();

        List<Bitmap> bmp = mulkler.get(position).getResimler();
        if(bmp != null && bmp.size() > 0){
            resim.setImageBitmap(bmp.get(0));
        }
        else{
            resim.setImageDrawable(context.getResources().getDrawable(R.drawable.home, context.getTheme()));
        }
        txtVwBaslik.setText(baslik);
        txtVwAdres.setText(adres);

        return custom_listView;
    }
}
