package com.example.doan3.DTO;

public class Danhsach {
    private int maxe;
    private String tenxe;
    private String phienban;
    private double giaxe;
    private String hinhxe;

    public Danhsach(int maxe, String tenxe, String phienban, double giaxe, String hinhxe) {
        this.maxe = maxe;
        this.tenxe = tenxe;
        this.phienban = phienban;
        this.giaxe = giaxe;
        this.hinhxe = hinhxe;
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
}
