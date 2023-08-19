package com.example.booking_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.List;

public class ArrAdpResimler extends BaseAdapter {

    List<Bitmap> bitmapList;
    Context context;
    LayoutInflater inflater;

    _veriKaynagi vk;

    int mulkID = 0;

    public ArrAdpResimler(Context context, List<Bitmap> bitmapList, int mulkID) {
        this.context = context;
        this.bitmapList = bitmapList;
        this.mulkID = mulkID;
        vk = new _veriKaynagi(context);
    }

    public ArrAdpResimler(Context context, List<Bitmap> bitmapList) {
        this.context = context;
        this.bitmapList = bitmapList;
        vk = new _veriKaynagi(context);
    }


    @Override
    public int getCount() {
        return bitmapList.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vk.mulkResimIDSec(mulkID, bitmapList.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_listview_resim, null);
        ImageView imageView = view.findViewById(R.id.customListviewResim_imageView);
        imageView.setImageBitmap(bitmapList.get(position));
        return view;
    }
}
