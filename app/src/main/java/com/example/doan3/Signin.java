package com.example.doan3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.DAO.QuanlyloaixeDAO;
import com.example.doan3.Database.CreateDatabase;

public class Signin extends AppCompatActivity implements View.OnClickListener {
    CreateDatabase database;
    EditText edttk,edtmk;
    Button btndn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        btndn =  findViewById(R.id.signintc);
        edttk=(EditText) findViewById(R.id.taikhoan);
        edtmk=(EditText) findViewById(R.id.matkhau);
        btndn.setOnClickListener(this);
        database = new CreateDatabase(this);
        database.ghidata("CREATE TABLE IF NOT EXISTS Taikhoan(hoten NVARCHAR(30),tk CHAR(20) PRIMARY KEY,mk CHAR(20) )");
    }
    public void btnDangnhap(){
        Intent intent = new Intent(Signin.this, Home.class);
        startActivity(intent);
    }
    public boolean ktdangnhap(){
        String tk = edttk.getText().toString();
        String mk = edtmk.getText().toString();
        if(tk.equals("Admin") && mk.equals("1")){
            Intent intent = new Intent(Signin.this, QuanlyloaixeDAO.class);
            startActivity(intent);
        }
        Cursor tkdn = database.docdata("SELECT * FROM Taikhoan WHERE tk='"+tk+"' AND mk='"+mk+"'");
        if(tkdn.getCount() != 0) {
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void onClick(View view) {
        int isCheck = view.getId();
        boolean kt = ktdangnhap();
        switch (isCheck) {
            case (R.id.signintc): {
                if (kt) {
                    Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                    btnDangnhap();
                } else {
                    Toast.makeText(this, "Account or password does not exist!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

    }
}
