package com.example.doan3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.DAO.DanhsachDAO;
import com.example.doan3.DAO.LoaixeDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ToiActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    ImageButton chongiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_toi);
        chongiohang = findViewById(R.id.giohangtoi);
        chongiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToiActivity.this, DanhsachDAO.class);
                startActivity(intent);
            }
        });
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_person);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_widgets:
                        startActivity(new Intent(getApplicationContext(),WidgetsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_person:
                        return true;
                }
                return false;
            }
        });
    }
}
