package com.daomanh.banhang.Entity;

public class ChiTietHoaDonModel {

    int maSanPham;
    String tenSanPham;
    String mauSanPham;
    int soLuong;
    int giaTien;

    public ChiTietHoaDonModel() {
    }

    public ChiTietHoaDonModel(int maSanPham, String tenSanPham, String mauSanPham, int soLuong, int giaTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.mauSanPham = mauSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(String mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonModel{" +
                "maSanPham=" + maSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", mauSanPham='" + mauSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", giaTien=" + giaTien +
                '}';
    }
}
