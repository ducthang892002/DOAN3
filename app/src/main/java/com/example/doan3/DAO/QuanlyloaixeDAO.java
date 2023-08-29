package com.example.doan3.DAO;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan3.Adapter.LoaixeAdapter;
import com.example.doan3.DTO.Loai;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.util.ArrayList;
import java.util.List;

public class QuanlyloaixeDAO extends AppCompatActivity {
    LoaixeAdapter adapter;
    List<Loai> loaiList;
    GridView grloaixe;
    CreateDatabase database;
    int maloai;
    Button btnadd,btnexit,btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_loaixe);
        Anhxa();
        loadloaixe();
        grloaixe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuanlyloaixeDAO.this,QuanlyxeDAO.class);
                intent.putExtra("maloai",loaiList.get(i).getMaloai());
                startActivity(intent);
            }
        });
        registerForContextMenu(grloaixe);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            DialogthemLoai();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.suaandxoa, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void DialogthemLoai(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.themtheloai);
        EditText edttheloai = dialog.findViewById(R.id.edt_ten_the_loai);
        EditText edtlinkanh = dialog.findViewById(R.id.edt_hinh_anh);
        btnadd = dialog.findViewById(R.id.btnthemtinh);
        btnexit = dialog.findViewById(R.id.btnthoatthem);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloaixe = edttheloai.getText().toString();
                String linkanh = edtlinkanh.getText().toString();
                database.insertLoai(tenloaixe,linkanh);
                dialog.dismiss();
                loadloaixe();
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
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.update:
                Loai loai = loaiList.get(index);
                int maloai = loai.getMaloai();
                String tenloai = loai.getTenloai();
                String hinhanh = loai.getHinh();
                DialogsuaLoai(maloai,tenloai,hinhanh);
                return true;
            case R.id.delete:
                Loai loai1 = loaiList.get(index);
                int maloaixoa = loai1.getMaloai();
                String tenloaixoa = loai1.getTenloai();
                String hinhloaixoa = loai1.getHinh();
                DialogXoaloai(maloaixoa,tenloaixoa,hinhloaixoa);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    private void DialogsuaLoai(int maloai,String tenloai,String hinhanh){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sualoaixe);
        EditText edttenloai = dialog.findViewById(R.id.suatenloai);
        EditText edtlinkanh = dialog.findViewById(R.id.suahinhanh);
        edttenloai.setText(tenloai);
        edtlinkanh.setText(hinhanh);
        btnupdate = dialog.findViewById(R.id.btnsualoai);
        btnexit = dialog.findViewById(R.id.btnthoatthem);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edttenloai.getText().toString();
                String hinhanh = edtlinkanh.getText().toString();
                database.updateLoai(maloai, tenloai, hinhanh);
                loadloaixe();
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
    private void DialogXoaloai(int maloai,String tenloai,String hinhloa) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Do you want to delete " + tenloai+ " ?");
        dialogxoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.deleteLoai(maloai);
                loadloaixe();
            }
        });
        dialogxoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
    private void loadloaixe() {
        Intent intent = getIntent();
        maloai = intent.getIntExtra("maloai",0);
        loaiList = new ArrayList<>();
        loaiList = database.getlistLoai();
        adapter = new LoaixeAdapter(this, R.layout.dulieuloaixe, loaiList);
        grloaixe.setAdapter(adapter);
    }
    private  void Anhxa(){
        grloaixe = findViewById(R.id.gvloaixe);
        database = new CreateDatabase(this);
    }
}
