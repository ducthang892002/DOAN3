package com.example.doan3.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.Adapter.LoaixeAdapter;
import com.example.doan3.Adapter.XeAdapter;
import com.example.doan3.DTO.Loai;
import com.example.doan3.DTO.Xe;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.util.ArrayList;
import java.util.List;

public class XeDAO extends AppCompatActivity {
    XeAdapter adapter;
    List<Xe> xeList=new ArrayList<>();
    GridView grxe;
    CreateDatabase database;
    EditText txttimkiem;
    int maloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xe);
        Anhxa();
        loadXe();
        grxe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(XeDAO.this,ChitietxeDAO.class);
                intent.putExtra("Tenxe",xeList.get(i).getTenxe());
                intent.putExtra("Phienban",xeList.get(i).getPhienban());
                intent.putExtra("Mota", xeList.get(i).getMota());
                intent.putExtra("Linkvideo", xeList.get(i).getLinkvideo());
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
        ArrayList<Xe> filteredList = new ArrayList<>();
        for (Xe l : xeList){
            if(l.getTenxe().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(l);
            }
        }
        adapter.filterList(filteredList);
    }
    private void Anhxa(){
        grxe = findViewById(R.id.gvxe);
        txttimkiem=findViewById(R.id.txttimkiem);
        database = new CreateDatabase(this);
    }
    private void loadXe(){
        Intent intent = getIntent();
        maloai = intent.getIntExtra("Maloai", 0);
        xeList = database.getlistXe(maloai);
        adapter = new XeAdapter(this,R.layout.dulieuxe,xeList);
        grxe.setAdapter(adapter);
    }
}
