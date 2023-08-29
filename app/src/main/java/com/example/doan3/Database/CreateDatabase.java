package com.example.doan3.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doan3.DTO.Danhsach;
import com.example.doan3.DTO.Loai;
import com.example.doan3.DTO.Xe;

import java.util.ArrayList;
import java.util.List;

public class CreateDatabase extends SQLiteOpenHelper {
    private static final String Name_Database = "banxe3.sqlite";
    private static final String Table_loaixe = "Loai";
    private static final String Table_xe = "Xe";
    private static final String Table_danhsach = "Danhsach";

    public CreateDatabase(@Nullable Context context) {
        super(context, Name_Database, null, 1);
    }
    public  void ghidata(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor docdata(String sql){
        SQLiteDatabase database= getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +Table_loaixe + "(" +
                "maloai  INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "tenloai TEXT ," +
                "hinhloai TEXT)");
        db.execSQL("CREATE TABLE " +Table_xe + "(" +
                "maxe  INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "tenxe TEXT ," +
                "phienban TEXT ," +
                "mota TEXT ," +
                "giaxe DOUBLE ," +
                "hinhxe TEXT,"+
                "linkvideo TEXT,"+
                "maloai INTEGER, FOREIGN KEY(maloai) REFERENCES "+Table_loaixe+" (maloai) ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE " +Table_danhsach + "(" +
                "maxe  INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "tenxe TEXT ," +
                "phienban TEXT ," +
                "giaxe DOUBLE ," +
                "hinhxe TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_loaixe);
        db.execSQL("DROP TABLE IF EXISTS " + Table_xe);
    }
    public boolean insertLoai(String tenloai,String hinhloai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",tenloai);
        contentValues.put("hinhloai",hinhloai);
        long result =db.insert(Table_loaixe,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean updateLoai(int maloai,String tenloai,String hinhloai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai",maloai);
        contentValues.put("tenloai",tenloai);
        contentValues.put("hinhloai",hinhloai);
        db.update(Table_loaixe,contentValues,"maloai='"+maloai+"'",null);
        return true;
    }
    public Integer deleteLoai(int maloai){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_loaixe,"maloai='"+maloai+"'",null);
    }
    public List<Loai> getlistLoai(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Loai> listloai = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Loai",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Loai loai = new Loai(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            listloai.add(loai);
            cursor.moveToNext();
        }
        return listloai;
    }
    public void insertXe(String tenxe,String phienban,String mota,double giaxe,String hinhxe,String linkvideo,int maloai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenxe",tenxe);
        contentValues.put("phienban",phienban);
        contentValues.put("mota",mota);
        contentValues.put("giaxe",giaxe);
        contentValues.put("hinhxe",hinhxe);
        contentValues.put("linkvideo",linkvideo);
        contentValues.put("maloai",maloai);
        db.insert(Table_xe,null,contentValues);
    }
    public boolean updateXe(int maxe, String tenxe, String phienban, String mota, double giaxe, String hinhxe, String linkvideo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maxe",maxe);
        contentValues.put("tenxe",tenxe);
        contentValues.put("phienban",phienban);
        contentValues.put("mota",mota);
        contentValues.put("giaxe",giaxe);
        contentValues.put("hinhxe",hinhxe);
        contentValues.put("linkvideo",linkvideo);
        db.update(Table_xe,contentValues,"maxe = '"+maxe+"'",null);
        return true;
    }
    public boolean ktXe(String tenxe){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +Table_danhsach+ " WHERE tenxe='"+tenxe+"'",null);
        if(cursor.getCount()!=0) {
            return true;
        }
        else{
            return false;
        }
    }
    public List<Xe> getlistXe(int maloai){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Xe> xeList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +Table_xe + " WHERE maloai= '"+maloai+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Xe xe = new Xe(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getDouble(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
            xeList.add(xe);
            cursor.moveToNext();
        }
        return xeList;
    }
    public void insertDanhsach(String tenxe,String phienban,double giaxe,String hinhxe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenxe",tenxe);
        contentValues.put("phienban",phienban);
        contentValues.put("giaxe",giaxe);
        contentValues.put("hinhxe",hinhxe);
        db.insert(Table_danhsach,null,contentValues);
    }
    public List<Danhsach> getlistDanhsach(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Danhsach> danhsachList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Danhsach" ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Danhsach danhsach = new Danhsach(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getString(4));
            danhsachList.add(danhsach);
            cursor.moveToNext();
        }
        return danhsachList;
    }
    public Integer xoadanhsach(int maxe){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_danhsach,"maxe='"+maxe+"'",null);
    }

    public int deletexe(int maxe) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_xe,"maxe='"+maxe+"'",null);
    }
}
