package com.example.doan3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan3.DTO.Loai;
import com.example.doan3.R;

import java.util.ArrayList;
import java.util.List;

public class LoaixeAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Loai> loaiList;

    public LoaixeAdapter(Context context, int layout, List<Loai> loaiList) {
        this.context = context;
        this.layout = layout;
        this.loaiList = loaiList;
    }
    public void filterList(ArrayList<Loai> filterlist) {
        loaiList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return loaiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHoder{
        TextView tenloai;
        ImageView hinhloai;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LoaixeAdapter.ViewHoder hoder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            hoder = new LoaixeAdapter.ViewHoder();
            hoder.tenloai = view.findViewById(R.id.tenloai);
            hoder.hinhloai = view.findViewById(R.id.img_hinh_anh);
            view.setTag(hoder);
        }else{
            hoder = (ViewHoder) view.getTag();
        }
        Loai loai = loaiList.get(i);
        hoder.tenloai.setText(loai.getTenloai());
        Glide.with(context).load(loai.getHinh()).error(R.drawable.ic_launcher_background).into(hoder.hinhloai);
        return view;
    }


}
