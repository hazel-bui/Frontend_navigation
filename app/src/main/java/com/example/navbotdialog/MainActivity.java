package com.example.navbotdialog;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.layoutMainBottomNavigationView);
        drawerLayout = findViewById(R.id.layoutMainDrawer);
        Toolbar toolbar = findViewById(R.id.layoutMainToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        ImageButton btnMenu = toolbar.findViewById(R.id.imgMainButtonMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Không mở menu kế bên
                /*drawerLayout.openDrawer(GravityCompat.START);*/
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

        if (savedInstanceState == null){
            replaceFragment(new ShopActivity());
        }

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.txtNavigationShop) {
                replaceFragment(new ShopActivity());
            } else if (id == R.id.txtNavigationSchedule) {
                replaceFragment(new ScheduleActivity());
            } else if (id == R.id.txtNavigationNews) {
                replaceFragment(new NewsActivity());
            } else if (id == R.id.txtNavigationProfile) {
                replaceFragment(new ProfileActivity());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layoutMainFrame, fragment)
                .commit();
    }
}