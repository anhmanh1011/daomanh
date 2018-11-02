package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "HOADON")
public class HoaDon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int maHoaDon;
    String tenKhachHang;
    String soDienThoai;
    Boolean tinhTrang;
    String ngaylap;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "maHoaDon")

    Set<ChiTietHoaDon> danhSachChiTietHoaDon;


    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }


}
