package com.example.doan3.DAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.Adapter.XeAdapter;
import com.example.doan3.DTO.Xe;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.util.ArrayList;
import java.util.List;

public class QuanlyxeDAO extends AppCompatActivity {
    ImageButton btnimageadd,btnimageupdate;
    Button btnexit, btnupdate,btnadd;
    GridView grxe;
    XeAdapter adapter;
    List<Xe> xeList;
    CreateDatabase database;
    int maloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_xe);
        Anhxa();
        loadXe();
        registerForContextMenu(grxe);
    }
    private void loadXe() {
        Intent intent = getIntent();
        maloai = intent.getIntExtra("maloai", 0);
        xeList = new ArrayList<>();
        xeList = database.getlistXe(maloai);
        adapter = new XeAdapter(this, R.layout.dulieuxe, xeList);
        grxe.setAdapter(adapter);
    }
    private void Anhxa() {
        grxe = findViewById(R.id.gvxe);
        database = new CreateDatabase(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            DialogthemXe();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.suaandxoa, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.update:
                Xe xe = xeList.get(index);
                int maxe = xe.getMaxe();
                String tenxe = xe.getTenxe();
                String phienban = xe.getPhienban();
                String motaxe = xe.getMota();
                Double giaxe = xe.getGiaxe();
                String hinhxe = xe.getHinhxe();
                String linkvideo =xe.getLinkvideo();
                SuaxeDialog(maxe,tenxe,phienban,motaxe,giaxe,hinhxe,linkvideo);
                return true;
            case R.id.delete:
                Xe xe1 = xeList.get(index);
                int maxe1 = xe1.getMaxe();
                String tenxe1 = xe1.getTenxe();
                XoaxeDialog(maxe1,tenxe1);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    private void DialogthemXe(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.themxe);
        EditText edttenxe = dialog.findViewById(R.id.edt_ten_xe);
        EditText edtphienban = dialog.findViewById(R.id.edt_phienban);
        EditText edtmota = dialog.findViewById(R.id.edt_mota);
        EditText edtgia = dialog.findViewById(R.id.edt_giaxe);
        EditText edtlinkanh = dialog.findViewById(R.id.edt_hinhanh);
        EditText edtlinkvideo = dialog.findViewById(R.id.edt_linkvideo);
        btnadd = dialog.findViewById(R.id.btnthemxe);
        btnexit = dialog.findViewById(R.id.btnthoat);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenxe = edttenxe.getText().toString();
                String phienban = edtphienban.getText().toString();
                String mota = edtmota.getText().toString();
                Double giaxe = Double.valueOf(edtgia.getText().toString());
                String linkanh = edtlinkanh.getText().toString();
                String linkvideo = edtlinkvideo.getText().toString();
                database.insertXe(tenxe,phienban,mota,giaxe,linkanh,linkvideo,maloai);
                dialog.dismiss();
                loadXe();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void SuaxeDialog(int maxe,String tenxe, String phienban, String motaxe, Double giaxe, String hinhxe,String linkvideo){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.suaxe);
        EditText edttenxe = dialog.findViewById(R.id.edt_ten_xe);
        EditText edtphienban = dialog.findViewById(R.id.edt_phienban);
        EditText edtmota = dialog.findViewById(R.id.edt_mota);
        EditText edtgia = dialog.findViewById(R.id.edt_giaxe);
        EditText edtlinkanh = dialog.findViewById(R.id.edt_hinhanh);
        EditText edtlinkvideo = dialog.findViewById(R.id.edt_linkvideo);
        edttenxe.setText(tenxe);
        edtphienban.setText(phienban);
        edtmota.setText(motaxe);
        edtgia.setText(String.valueOf(giaxe));
        edtlinkanh.setText(hinhxe);
        edtlinkvideo.setText(linkvideo);
        btnupdate = dialog.findViewById(R.id.btnsuaxe);
        btnexit = dialog.findViewById(R.id.btnthoatsua);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenxe = edttenxe.getText().toString();
                String phienban = edtphienban.getText().toString();
                String mota = edtmota.getText().toString();
                Double giaxe = Double.valueOf(edtgia.getText().toString());
                String hinhxe = edtlinkanh.getText().toString();
                String linkvideo = edtlinkvideo.getText().toString();
                database.updateXe(maxe,tenxe,phienban,mota,giaxe,hinhxe,linkvideo);
                loadXe();
                dialog.dismiss();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void XoaxeDialog(int maxe,String tenxe) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Do you want to delete " + tenxe+ " car?");
        dialogxoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.deletexe(maxe);
                loadXe();
            }
        });
        dialogxoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
}
