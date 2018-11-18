package com.daomanh.banhang.Entity;

import java.util.List;

public class Json_SanPham {
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public DanhMucSanPham getDanhMucSanPham() {
        return danhMucSanPham;
    }

    public void setDanhMucSanPham(DanhMucSanPham danhMucSanPham) {
        this.danhMucSanPham = danhMucSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public List<ChiTietSanPham> getDsChiTietSanPham() {
        return dsChiTietSanPham;
    }

    public void setDsChiTietSanPham(List<ChiTietSanPham> dsChiTietSanPham) {
        this.dsChiTietSanPham = dsChiTietSanPham;
    }

    public List<KhuyenMai> getDanhSachKhuyenMai() {
        return danhSachKhuyenMai;
    }

    public void setDanhSachKhuyenMai(List<KhuyenMai> danhSachKhuyenMai) {
        this.danhSachKhuyenMai = danhSachKhuyenMai;
    }

    int maSanPham;


    DanhMucSanPham danhMucSanPham;


    String tenSanPham;
    String giaTien;
    String moTa;
    String hinhSanPham;

    List<ChiTietSanPham> dsChiTietSanPham;


    List<KhuyenMai> danhSachKhuyenMai;

    public Json_SanPham() {
    }

    public Json_SanPham(int maSanPham, DanhMucSanPham danhMucSanPham, String tenSanPham, String giaTien, String moTa, String hinhSanPham, List<ChiTietSanPham> dsChiTietSanPham, List<KhuyenMai> danhSachKhuyenMai) {
        this.maSanPham = maSanPham;
        this.danhMucSanPham = danhMucSanPham;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.hinhSanPham = hinhSanPham;
        this.dsChiTietSanPham = dsChiTietSanPham;
        this.danhSachKhuyenMai = danhSachKhuyenMai;
    }
}
