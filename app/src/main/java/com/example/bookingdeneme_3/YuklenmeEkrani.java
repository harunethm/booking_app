package com.example.bookingdeneme_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class YuklenmeEkrani extends AppCompatActivity {

    Context con = null;
    _veriKaynagi vk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acilis_ekrani);
        con = this;
        vk = new _veriKaynagi(con);

        Thread tt = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent;
                    if(vk.kullanicilariListele().size() > 0){
                        intent = new Intent(YuklenmeEkrani.this, GirisEkrani.class);
                    }
                    else{
                        intent = new Intent(YuklenmeEkrani.this, KayitEkrani.class);
                    }
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                }
            }
        };
        tt.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
