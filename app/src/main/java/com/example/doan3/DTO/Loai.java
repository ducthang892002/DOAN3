package com.example.doan3.DTO;

public class Loai {
    private int maloai;
    private String tenloai;
    private String hinh;

    public Loai(int maloai, String tenloai, String hinh) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.hinh = hinh;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
