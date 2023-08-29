package com.example.doan3.DAO;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.doan3.Adapter.DanhsachAdapter;
import com.example.doan3.DTO.Danhsach;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class DanhsachDAO extends AppCompatActivity {
    ListView lvdanhsach;
    DanhsachAdapter adapter;
    List<Danhsach> danhsachList = new ArrayList<>();
    CreateDatabase database;
    String giaxe, tenxe, soluong, maxe;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach);
        Anhxa();
        loaddanhsach();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("mych","My channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"mych").setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("Order Success").setContentText("Please come to the store in 15 days");
        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    private void Anhxa() {
        lvdanhsach = findViewById(R.id.lvdanhsach);
        database = new CreateDatabase(this);
        Intent intent = getIntent();
        giaxe = intent.getStringExtra("Giaxe");
        tenxe = intent.getStringExtra("Tenxe");
        soluong = intent.getStringExtra("Soluong");
        maxe = intent.getStringExtra("Maxe");
    }

    private void loaddanhsach() {
        danhsachList = database.getlistDanhsach();
        adapter = new DanhsachAdapter(this, R.layout.dulieudanhsach, danhsachList);
        lvdanhsach.setAdapter(adapter);
    }


    public void xoadanhsach(String tenxe, int maxe) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);

        dialogxoa.setMessage("Do you want to delete " + tenxe + " car?");
        dialogxoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.xoadanhsach(maxe);
                Toast.makeText(DanhsachDAO.this, "Delete " + tenxe, Toast.LENGTH_SHORT).show();
                loaddanhsach();
            }
        });
        dialogxoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialogxoa.show();
    }


    public void thanhtoanhd(double giaxe, String tenxe, int maxe) {
        AlertDialog.Builder dialogtinh = new AlertDialog.Builder(this);
        dialogtinh.setMessage("You choose to pay the" + tenxe + "car with the price: " + giaxe + "  ?");
        dialogtinh.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                notificationManagerCompat.notify(1,notification);
                Toast.makeText(DanhsachDAO.this,
                        "The car has been booked and you will have to come to the store to pick it up 15 days later", Toast.LENGTH_SHORT).show();
                loaddanhsach();
            }
        });
        dialogtinh.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialogtinh.show();
    }
    public void danhgiasp() {
        Dialog dialogdanhgia = new Dialog(this);
        dialogdanhgia.setContentView(R.layout.danhgiasao);
        dialogdanhgia.show();
    }
}
