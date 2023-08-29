package com.example.doan3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doan3.DTO.Loai;
import com.example.doan3.DTO.Xe;
import com.example.doan3.Database.CreateDatabase;
import com.example.doan3.R;

import java.util.ArrayList;
import java.util.List;

public class XeAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Xe> xeList;
    public XeAdapter(Context context, int layout, List<Xe> xeList) {
        this.context = context;
        this.layout = layout;
        this.xeList = xeList;
    }
    public void filterList(ArrayList<Xe> filterlist) {
        xeList = filterlist;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return xeList.size();
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
        TextView tenxe,phienban,giaxe;
        ImageView hinhxe,imgthem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        XeAdapter.ViewHoder hoder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            hoder = new XeAdapter.ViewHoder();
            hoder.tenxe = view.findViewById(R.id.tenxe);
            hoder.phienban = view.findViewById(R.id.phienban);
            hoder.giaxe = view.findViewById(R.id.giaxe);
            hoder.hinhxe = view.findViewById(R.id.hinhxe);
            hoder.imgthem = view.findViewById(R.id.themvaogio);
            view.setTag(hoder);
        }else{
            hoder = (XeAdapter.ViewHoder) view.getTag();
        }
        Xe xe = xeList.get(i);
        hoder.tenxe.setText(xe.getTenxe());
        hoder.phienban.setText(xe.getPhienban());
        hoder.giaxe.setText(String.valueOf(xe.getGiaxe()));
        Glide.with(context).load(xe.getHinhxe()).error(R.drawable.ic_launcher_background).into(hoder.hinhxe);
        hoder.imgthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateDatabase database = new CreateDatabase(context);
                if(database.ktXe(xe.getTenxe()) == true){
                    Toast.makeText(context, xe.getTenxe() +" already in your cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.insertDanhsach(xe.getTenxe(),xe.getPhienban(),xe.getGiaxe(),xe.getHinhxe());
                    Toast.makeText(context, "Added " +xe.getTenxe() +" to your cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
