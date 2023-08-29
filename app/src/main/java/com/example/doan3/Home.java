package com.example.doan3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.doan3.DAO.DanhsachDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    BottomNavigationView navigationView;
    ViewFlipper viewFlipper;
    ImageButton giohang11;
    private ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        giohang11 = findViewById(R.id.giohang1);
        giohang11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, DanhsachDAO.class);
                startActivity(intent);
            }
        });
        imageSlider = findViewById(R.id.imagesilde1);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://d1csarkz8obe9u.cloudfront.net/posterpreviews/car-sales-flyer-design-template-b86fc577f20885de92bdfbed25d0699e_screen.jpg?ts=1594179442", ScaleTypes.FIT));
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
        viewFlipper = findViewById(R.id.viewfiiper);
        ActionViewFliper();
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_widgets:
                        startActivity(new Intent(getApplicationContext(),WidgetsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
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
    private void ActionViewFliper() {
        List<String> quangcao = new ArrayList<>();
        quangcao.add("https://images.summitmedia-digital.com/topgear/images/2020/03/23/2020-bmw-2-series-m2-main-1584967054.jpg");
        quangcao.add("https://imgd.aeplcdn.com/0x0/cw/ec/37095/BMW-Z4-Roadster-Right-Front-Three-Quarter-153914.jpg?wm=0");
        quangcao.add("https://file.kelleybluebookimages.com/kbb/base/house/2022/2022-BMW-i4-FrontSide_BMI42201_640x480.jpg");
        quangcao.add("https://imgd.aeplcdn.com/600x337/cw/ec/30181/RollsRoyce-Phantom-VIII-Exterior-124069.jpg?wm=0&q=75");
        for (int i = 0; i < quangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide1);
        Animation slide2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide2);
        viewFlipper.setInAnimation(slide1);
        viewFlipper.setOutAnimation(slide2);
    }
}
