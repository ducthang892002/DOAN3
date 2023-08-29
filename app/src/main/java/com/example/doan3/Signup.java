package com.example.doan3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.Database.CreateDatabase;

public class Signup extends AppCompatActivity {
    CreateDatabase database;
    Button btndk1;
    EditText edthoten,edttkdk,edtmkdk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        btndk1 = findViewById(R.id.signuptc);
        edttkdk = findViewById(R.id.taikhoan);
        edtmkdk = findViewById(R.id.matkhau);
        edthoten = findViewById(R.id.name);
        database = new CreateDatabase(this);
        database.ghidata("CREATE TABLE IF NOT EXISTS Taikhoan(hoten NVARCHAR(30),tk CHAR(20) PRIMARY KEY,mk CHAR(20) )");
    }
    public void btnDangky(){
        Intent intent = new Intent(Signup.this, Signin.class);
        startActivity(intent);
    }
    public Boolean ktdangky(){
        String tk =edttkdk.getText().toString();
        Cursor kttk = database.docdata("SELECT * FROM Taikhoan WHERE tk='"+tk+"'");
        if(kttk.getCount()!=0) {
            return true;
        }
        else{
            return false;
        }
    }
    public void onClickSignup(View view){
        String tk =edttkdk.getText().toString();
        String mk= edtmkdk.getText().toString();
        String hoten =edthoten.getText().toString();
        boolean kttk =ktdangky();
        if(kttk) {
            Toast.makeText(this, "At an existing account!", Toast.LENGTH_SHORT).show();
        }
        else if(tk.equals("") || mk.equals("")|| hoten.equals("")){
            Toast.makeText(this, "Please enter all information!", Toast.LENGTH_SHORT).show();
        }
        else{
            database.ghidata("INSERT INTO Taikhoan VALUES ('"+hoten+"','"+tk+"','"+mk+"')");
            Toast.makeText(this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
            btnDangky();
        }
    }

}
