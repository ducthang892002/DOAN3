package com.example.doan3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.doan3.DAO.LoaixeDAO;
import com.example.doan3.DAO.QuanlyloaixeDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class WidgetsActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    ImageButton chonloai;

    private ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_widgets);
        imageSlider = findViewById(R.id.imagesilde);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/ferrari-logo-750x1100.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/lamborghini-logo-1000x1100.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/maserati-logo-2015-black.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/logo/Rolls-Royce-logo-2048x2048.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/bentley-logo-1400x800.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/logo/Bugatti-logo-1024x768.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/logo/Mercedes-Benz-logo-2011-1920x1080.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/porsche-logo-2100x1100.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/bmw-logo-1997-1200x1200.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/car-logos/audi-logo-2009-show.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.carlogos.org/logo/Lexus-logo-1988-640x266.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        chonloai= findViewById(R.id.chonloaixe);
        chonloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WidgetsActivity.this, LoaixeDAO.class);
                startActivity(intent);
            }
        });

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_widgets);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_widgets:
                        return true;

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_person:
                        startActivity(new Intent(getApplicationContext(),ToiActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

}
