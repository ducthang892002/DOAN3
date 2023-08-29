package com.example.doan3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doan3.DAO.DanhsachDAO;
import com.example.doan3.DTO.Danhsach;
import com.example.doan3.DTO.Xe;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.util.List;

public class DanhsachAdapter extends BaseAdapter {
    DanhsachDAO context;
    int layout;
    List<Danhsach> danhsachList;
    TextView soluong;
    int soluong1 = 10;
    String soluong2="10";

    public DanhsachAdapter(DanhsachDAO context, int layout, List<Danhsach> danhsachList) {
        this.context = context;
        this.layout = layout;
        this.danhsachList = danhsachList;
    }
    @Override
    public int getCount() {
        return danhsachList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHoder {
        TextView tenxe, phienban,gia,soluong;
        ImageView hinhxe,xoa,tang,giam,thanhtoan,danhgia;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DanhsachAdapter.ViewHoder hoder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            hoder = new DanhsachAdapter.ViewHoder();
            hoder.tenxe = view.findViewById(R.id.tenxe1);
            hoder.phienban = view.findViewById(R.id.phienban1);
            hoder.gia = view.findViewById(R.id.giaxe1);
            hoder.hinhxe = view.findViewById(R.id.imghinh);
            hoder.xoa = view.findViewById(R.id.xoa);
            hoder.tang = view.findViewById(R.id.tang);
            hoder.giam = view.findViewById(R.id.giam);
            hoder.soluong = view.findViewById(R.id.soluong);
            hoder.thanhtoan=view.findViewById(R.id.thanhtoan);
            hoder.danhgia=view.findViewById(R.id.danhgia);
            view.setTag(hoder);
        }else{
            hoder = (DanhsachAdapter.ViewHoder) view.getTag();
        }
        Danhsach ds = danhsachList.get(i);
        hoder.tenxe.setText(ds.getTenxe());
        hoder.phienban.setText(ds.getPhienban());
        hoder.gia.setText(String.valueOf(ds.getGiaxe()));
        Glide.with(context).load(ds.getHinhxe()).error(R.drawable.ic_launcher_background).into(hoder.hinhxe);
        hoder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.xoadanhsach(ds.getTenxe(),ds.getMaxe());
            }
        });
//        hoder.giam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                soluong1=soluong1-1;
//                soluong2=String.valueOf(soluong1);
//                soluong.setText(soluong2);
//            }
//        });
//        hoder.tang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                soluong1=soluong1+1;
//                soluong2=String.valueOf(soluong1);
//                soluong.setText(soluong2);
//            }
//        });
        hoder.thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.thanhtoanhd(ds.getGiaxe(),ds.getTenxe(),ds.getMaxe());
            }
        });
        hoder.danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.danhgiasp();
            }
        });
        return view;
    }
}
