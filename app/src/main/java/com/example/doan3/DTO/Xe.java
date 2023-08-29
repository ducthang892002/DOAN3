package com.example.doan3.DTO;

public class Xe {
    private int maxe;
    private String tenxe;
    private String phienban;
    private String mota;
    private double giaxe;
    private String hinhxe;
    private String linkvideo;
    private int maloai;

    public Xe(int maxe, String tenxe, String phienban, String mota, double giaxe, String hinhxe, String linkvideo, int maloai) {
        this.maxe = maxe;
        this.tenxe = tenxe;
        this.phienban = phienban;
        this.mota = mota;
        this.giaxe = giaxe;
        this.hinhxe = hinhxe;
        this.linkvideo = linkvideo;
        this.maloai = maloai;
    }

    public int getMaxe() {
        return maxe;
    }

    public void setMaxe(int maxe) {
        this.maxe = maxe;
    }

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public String getPhienban() {
        return phienban;
    }

    public void setPhienban(String phienban) {
        this.phienban = phienban;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public double getGiaxe() {
        return giaxe;
    }

    public void setGiaxe(double giaxe) {
        this.giaxe = giaxe;
    }

    public String getHinhxe() {
        return hinhxe;
    }

    public void setHinhxe(String hinhxe) {
        this.hinhxe = hinhxe;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
