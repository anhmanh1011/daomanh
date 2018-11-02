package com.daomanh.banhang.Entity;

public class GioHang {

   private SanPham sanPham = new SanPham();
   private MauSanPham mauSanPham = new MauSanPham();

   private int soLuong;

    public GioHang() {
    }

    public GioHang(SanPham sanPham, MauSanPham mauSanPham, int soLuong) {
        this.sanPham = sanPham;
        this.mauSanPham = mauSanPham;
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public MauSanPham getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(MauSanPham mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "sanPham=" + sanPham +
                ", mauSanPham=" + mauSanPham +
                ", soLuong=" + soLuong +
                '}';
    }
}
