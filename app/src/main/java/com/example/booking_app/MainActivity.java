package com.example.booking_app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //region tanımlamalar
    Toolbar tb;
    BottomNavigationView bnv;
    Fragment f;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //region tanımlamalar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

        tb = (Toolbar) findViewById(R.id.activityMain_toolBar);
        bnv = (BottomNavigationView) findViewById(R.id.activityMain_navBar);
        //endregion

        //region bottom navi bar click
        getSupportFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, new MevcutMulkler()).commit();
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                f = null;
                String title = "";
                int dw = -1;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        f = new MevcutMulkler();
                        title = "Mevcut Mülkler";
                        dw = R.drawable.home;
                        break;
                    case R.id.nav_search:
                        f = new Ara();
                        title = "Mülk Ara";
                        dw = R.drawable.search;
                        break;
                    case R.id.nav_messages:
                        f = new Mesajlar();
                        title = "Mesajlar";
                        dw = R.drawable.message;
                        break;
                    case R.id.nav_profile:
                        f = new Profil();
                        title = "Profil";
                        dw = R.drawable.profile;
                        break;
                    default:
                        break;
                }
                tb.setTitle(title);
                tb.setNavigationIcon(dw);
                getSupportFragmentManager().beginTransaction().replace(R.id.activityMain_fragment, f).commit();
                return true;
            }
        });
        //endregion
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }
}