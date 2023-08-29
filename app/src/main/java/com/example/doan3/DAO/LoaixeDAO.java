package com.example.doan3.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.Adapter.LoaixeAdapter;
import com.example.doan3.DTO.Loai;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.util.ArrayList;
import java.util.List;

public class LoaixeDAO extends AppCompatActivity {
    LoaixeAdapter adapter;
    List<Loai> loaiList;
    GridView grloai;
    CreateDatabase database;
    EditText txttimkiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loaixe);
        Anhxa();
        loadLoai();
        grloai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LoaixeDAO.this,XeDAO.class);
                intent.putExtra("Maloai",loaiList.get(i).getMaloai());
                startActivity(intent);
            }
        });
        txttimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }
    private void filter(String text){
        ArrayList<Loai> filteredList = new ArrayList<>();
        for (Loai l : loaiList){
            if(l.getTenloai().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(l);
            }
        }
        adapter.filterList(filteredList);
    }
    private  void Anhxa(){
        grloai = findViewById(R.id.gvloaixe);
        txttimkiem=findViewById(R.id.txttimkiem);
        database = new CreateDatabase(this);
    }
    private void loadLoai(){
        loaiList = new ArrayList<>();
        loaiList = database.getlistLoai();
        adapter = new LoaixeAdapter(this, R.layout.dulieuloaixe, loaiList);
        grloai.setAdapter(adapter);
    }

}
