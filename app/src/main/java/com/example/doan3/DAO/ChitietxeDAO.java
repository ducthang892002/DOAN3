package com.example.doan3.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.R;
import com.example.doan3.Xemvideo;

public class ChitietxeDAO extends AppCompatActivity {
    TextView txtten,txtmota,txtphienban;
    ImageButton imgvideo;
    String ten,mota,phienban,linkvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietxe);
        Anhxa();
        loadnoidung();
        imgvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChitietxeDAO.this, Xemvideo.class);
                intent.putExtra("Linkvideo",linkvideo);
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        txtten = findViewById(R.id.txttenctxe);
        txtmota = findViewById(R.id.txtmota);
        txtphienban = findViewById(R.id.txtphienbanct);
        imgvideo = findViewById(R.id.btnxemvideo);
        Intent intent = getIntent();
        ten = intent.getStringExtra("Tenxe");
        mota = intent.getStringExtra("Mota");
        phienban = intent.getStringExtra("Phienban");
        linkvideo =intent.getStringExtra("Linkvideo");
    }
    private void loadnoidung(){
        txtten.setText(ten);
        txtmota.setText(mota);
        txtphienban.setText(phienban);
    }
}
